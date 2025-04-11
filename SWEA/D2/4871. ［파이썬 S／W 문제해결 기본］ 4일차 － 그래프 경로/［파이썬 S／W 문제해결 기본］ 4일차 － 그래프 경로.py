T = int(input())

for t in range(1, T+1):
    V, E = map(int, input().split())
    # sparse
    graph = {v: [] for v in range(1, V+1)}
    for _ in range(E):
        u, v = map(int, input().split())
        graph[u].append(v)

    s, g = map(int, input().split())

    visited = [0] * (V + 1)
    visited[s] = 1
    stack = [s]
    while stack:
        u = stack.pop()
        for v in graph[u]:
            if v not in visited:
                visited[v] = 1
                stack.append(v)

    print(f"#{t} {visited[g]}")
