#include <stdio.h>
#include <string.h>

#define MAX 1001

int adj[MAX][MAX];
int adj_count[MAX];

int match[MAX];
int visited[MAX];

int dfs(int x) {
  for (int i = 0; i < adj_count[x]; ++i) {
    int work = adj[x][i];

    if (visited[work])
      continue;
    visited[work] = 1;

    if (match[work] == 0 || dfs(match[work])) {
      match[work] = x;
      return 1;
    }
  }
  return 0;
}

int main() {
  int N = 3;

  adj[1][adj_count[1]++] = 1;
  adj[1][adj_count[1]++] = 2;

  adj[2][adj_count[2]++] = 1;
  adj[3][adj_count[3]++] = 2;

  int max_match = 0;

  memset(match, 0, sizeof(match));

  for (int i = 1; i <= N; ++i) {
    memset(visited, 0, sizeof(visited));

    if (dfs(i)) {
      ++max_match;
    }
  }

  printf("max matching: %d\n", max_match);

  return 0;


}