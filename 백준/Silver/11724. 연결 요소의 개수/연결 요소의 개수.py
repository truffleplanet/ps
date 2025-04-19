import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().strip().split())
g = {i: [] for i in range(1, N + 1)}
for _ in range(M):
    u, v = map(int, input().strip().split())
    g[u].append(v)
    g[v].append(u)

visited = [False] * (N + 1)

def bfs(start):
    q = deque([start])
    visited[start] = True

    while q:
        u = q.popleft()
        for v in g[u]:
            if not visited[v]:
                visited[v] = True
                q.append(v)

count = 0
for i in range(1, N + 1):
    if not visited[i]:
        bfs(i)
        count += 1

print(count)
