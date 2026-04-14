#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using std::ios_base;
using std::cin;
using std::cout;
using std::vector;
using std::pair;
using std::priority_queue;

constexpr int MAX_V = 100000;
constexpr long long INF = 1e18;

bool isInView[MAX_V];
long long dist[MAX_V];

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int V, E;
  cin >> V >> E;

  for (int i{}; i < V; i++)
    cin >> isInView[i];


  vector<vector<pair<int, int>>> graph(V);
  for (int i{}; i < E; i++)
  {
    int u, v, w;
    cin >> u >> v >> w;
    if (u == V - 1 || v == V - 1 || !(isInView[u] || isInView[v]))
    {
      graph[u].emplace_back(v, w);
      graph[v].emplace_back(u, w);
    }
  }

  std::fill_n(dist, V, INF);
  dist[0] = 0;

  priority_queue<pair<long long, int>,
                 vector<pair<long long, int>>,
                 std::greater<>> pq;

  pq.emplace(0LL, 0);

  while (!pq.empty())
  {
    auto [total, cur]  = pq.top();
    pq.pop();

    if (dist[cur] < total) continue;

    if (cur == V - 1) break;

    for (const auto& [next, w] : graph[cur])
    {
      if (dist[next] > total + w)
      {
        dist[next] = total + w;
        pq.emplace(total + w, next);
      }
    }
  }

  if (dist[V - 1] == INF)
    cout << -1 << '\n';
  else
    cout << dist[V - 1] << '\n';

  return 0;

}

