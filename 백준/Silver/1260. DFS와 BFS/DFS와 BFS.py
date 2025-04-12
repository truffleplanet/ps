import sys
from collections import deque


def dfs(graph, v):
    visited = [0] * (len(graph) + 1)
    path = []
    stack = [V]
    
    while stack:
        node = stack.pop()
        if not visited[node]:
            visited[node] = 1
            path.append(node)
            for u in reversed(graph[node]):
                if not visited[u]:
                    stack.append(u)
    return path


def bfs(graph, v):
    q = deque()
    q.append(v)
    visited = [0] * (len(graph) + 1)
    visited[v] = 1
    path = []
    while q:
        node = q.popleft()
        path.append(node)
        for u in graph[node]:
            if not visited[u]:
                visited[u] = 1
                q.append(u)
    return path


N, M, V = map(int, sys.stdin.readline().strip().split())

graph = {u:[] for u in range(1, N+1)}
for _ in range(M):
    u, v = map(int, sys.stdin.readline().strip().split())
    graph[u].append(v)
    graph[v].append(u)
for k, v in graph.items():
    v.sort()

print(' '.join(map(str, dfs(graph, V))))
print(' '.join(map(str, bfs(graph, V))))
