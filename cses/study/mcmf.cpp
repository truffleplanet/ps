#include <string.h>

#define MAX_V 1001 // 1 based
#define MAX_E 5001
#define Q_SIZE 1001
#define INF 0x3f3f3f3f


struct Edge {
  int to;
  int capacity;
  int flow;
  int cost;
  int rev;
  int next;
};

// graph
Edge edge_pool[MAX_E];
int head[MAX_V];
int edge_cnt;

// spfa
int dist[MAX_V];
int parent[MAX_V];
int parent_edge[MAX_V];

bool in_queue[MAX_V];
int q[MAX_V + 1];

// mcmf
int total_flow;
int total_cost;


void init() {
  edge_cnt = 0;
  memset(head, 0, sizeof(head));
}

void add_edge(int from, int to, int capacity, int flow, int cost) {
  ++edge_cnt;
  int f_idx = edge_cnt;
  edge_pool[f_idx].to = to;
  edge_pool[f_idx].capacity = capacity;
  edge_pool[f_idx].flow = 0;
  edge_pool[f_idx].cost = cost;
  edge_pool[f_idx].next = head[from];
  head[from] = f_idx;

  ++edge_cnt;
  int r_idx = edge_cnt;
  edge_pool[r_idx].to = from;
  edge_pool[r_idx].capacity = 0;
  edge_pool[r_idx].flow = 0;
  edge_pool[r_idx].cost = -cost;
  edge_pool[r_idx].next = head[to];
  head[to] = r_idx;

  edge_pool[f_idx].rev = r_idx;
  edge_pool[r_idx].rev = f_idx;
}

bool spfa(int s, int t) {
  memset(dist, 0x3f, sizeof(dist));
  memset(in_queue, false, sizeof(in_queue));
  memset(parent, -1, sizeof(parent));

  int q_head = 0;
  int q_tail = 0;
  dist[s] = 0;
  q[q_tail] = s;
  q_tail = (q_tail + 1) % Q_SIZE;
  in_queue[s] = true;

  while (q_head != q_tail) {
    int u = q[q_head];
    q_head = (q_head + 1) % Q_SIZE;
    in_queue[u] = false;

    for (int i = head[u]; i != 0; i = edge_pool[i].next) {
      Edge* e = &edge_pool[i];

      if (e->capacity - e->flow > 0 && dist[e->to] > dist[u] + e->cost) {
        dist[e->to] = dist[u] + e->cost;
        parent[e->to] = u;
        parent_edge[e->to] = i;

        if (!in_queue[e->to]) {
          q[q_tail] = e->to;
          q_tail = (q_tail + 1) % Q_SIZE;
          in_queue[e->to] = true;
        }
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
      int e_idx = parent_edge[curr];
      int remain_capacity = edge_pool[e_idx].capacity - edge_pool[e_idx].flow;

      if (remain_capacity < push_flow) {
        push_flow = remain_capacity;
      }
    }

    total_flow += push_flow;
    total_cost += push_flow * dist[t];

    for (int curr = t; curr != s; curr = parent[curr]) {
      int e_idx = parent_edge[curr];
      int r_idx = edge_pool[e_idx].rev;

      edge_pool[e_idx].flow += push_flow;
      edge_pool[r_idx].flow -= push_flow;
    }
  }
}

int main() {

}
