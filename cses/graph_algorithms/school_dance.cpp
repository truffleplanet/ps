#include <stdio.h>
#include <string.h>

#define MAX 501

int adj[MAX][MAX];
int adj_count[MAX];
int match[MAX];
int visited[MAX];

int dfs(int x) {
  for (int i = 0; i < adj_count[x]; ++i) {
    int pair_girl = adj[x][i];

    if (visited[pair_girl])
      continue;
    visited[pair_girl] = 1;

    if (match[pair_girl] == 0 || dfs(match[pair_girl])) {
      match[pair_girl] = x;
      return 1;
    }
  }
  return 0;
}

int main() {
  int n, m, k;

  scanf("%d %d %d", &n, &m, &k);

  for (int i = 0; i < k; ++i) {
    int a, b;
    scanf("%d %d", &a, &b);
    adj[a][adj_count[a]++] = b;
  }

  int max_match = 0;
  memset(match, 0, sizeof(match));

  for (int i = 1; i <= n; ++i) {
    memset(visited, 0, sizeof(visited));
    if (dfs(i)) {
      ++max_match;
    }
  }

  printf("%d\n", max_match);

  for (int i = 1; i <= m; ++i) {
    if (match[i] != 0) {
      printf("%d %d\n", match[i], i);
    }
  }

  return 0;
}