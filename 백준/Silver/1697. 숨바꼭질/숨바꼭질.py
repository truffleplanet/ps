from collections import deque

def bfs(n, k):
    MAX = 200000
    visited = [0] * (MAX + 1)
    q = deque([n])
    visited[n] = 1

    while q:
        x = q.popleft()
        if x == k:
            return visited[x] - 1
        
        for nx in (x - 1, x + 1, x * 2):
            if 0 <= nx <= MAX and not visited[nx]:
                visited[nx] = visited[x] + 1
                q.append(nx)

N, K = map(int, input().split())

print(bfs(N, K))
