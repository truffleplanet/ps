#include <stdio.h>
#include <string.h>

#define RELAX(u, v)                                                            \
  if (res_cap[u][v] > 0 && dist[v] > dist[u] + cost[u][v]) {                   \
    dist[v] = dist[u] + cost[u][v];                                            \
    parent[v] = u;                                                             \
    if (!in_queue[v]) {                                                        \
      q[++tail] = v;                                                           \
      in_queue[v] = true;                                                      \
    }                                                                          \
  }

constexpr int MAX_V = 512;
constexpr int Q_SIZE = 80001;
constexpr int INF = 0x3f3f3f3f;

int res_cap[MAX_V][MAX_V];
int cost[MAX_V][MAX_V];

int dist[MAX_V];
int parent[MAX_V];
bool in_queue[MAX_V];
int q[Q_SIZE];

int total_flow;
int total_cost;

int N, S, T;

void add_edge(int u, int v, int c) {
  res_cap[u][v] = 1;
  res_cap[v][u] = 0;

  cost[u][v] = c;
  cost[v][u] = -c;
}

bool spfa(int s, int t) {
  memset(dist, 0x3f, sizeof(dist));
  memset(in_queue, false, sizeof(in_queue));
  memset(parent, -1, sizeof(parent));

  int head = -1;
  int tail = -1;
  dist[s] = 0;
  q[++tail] = s;
  in_queue[s] = true;

  while (head != tail) {
    int u = q[++head];
    in_queue[u] = false;

    if (u == T) {
      continue;
    }

    if (u == S) {
      for (int v = 1; v <= N; ++v) {
        RELAX(u, v);
      }
    } else if (u <= N) {
      RELAX(u, S);
      for (int v = N + 1; v < T; ++v) {
        RELAX(u, v);
      }
    } else {
      RELAX(u, T);
      for (int v = 1; v <= N; ++v) {
        RELAX(u, v);
      }
    }
  }
  return dist[t] != INF;
}

void mcmf(int s, int t) {
  total_flow = 0;
  total_cost = 0;

  while (spfa(s, t)) {
    int push_flow = INF;
    for (int curr = t; curr != s; curr = parent[curr]) {
      int prev = parent[curr];
      if (res_cap[prev][curr] < push_flow) {
        push_flow = res_cap[prev][curr];
      }
    }
    total_flow += push_flow;
    total_cost += push_flow * dist[t];

    for (int curr = t; curr != s; curr = parent[curr]) {
      int prev = parent[curr];

      res_cap[prev][curr] -= push_flow;
      res_cap[curr][prev] += push_flow;
    }
  }
}

int main() {
  scanf("%d", &N);
  S = 0;
  T = 2 * N + 1;

  for (int i = 1; i <= N; ++i) {
    add_edge(S, i, 0);
  }

  for (int i = N + 1; i < T; ++i) {
    add_edge(i, T, 0);
  }

  for (int i = 1; i <= N; ++i) {
    for (int j = N + 1; j < T; ++j) {
      int c;
      scanf("%d", &c);
      add_edge(i, j, c);
    }
  }

  mcmf(S, T);

  printf("%d\n", total_cost);

  for (int i = 1; i <= N; ++i) {
    for (int j = N + 1; j < T; ++j) {
      if (res_cap[i][j] == 0) {
        printf("%d %d\n", i, j - N);
      }
    }
  }
}