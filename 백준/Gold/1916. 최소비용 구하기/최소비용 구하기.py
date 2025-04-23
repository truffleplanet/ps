import sys
import heapq
input = sys.stdin.readline

# if D[u] + w(u, v) < D[v]
#    D[v] = D[u] + w(u, v)

N = int(input())
M = int(input())
graph = {k: [] for k in range(1, N+1)}

for _ in range(M):
    u, v, w = map(int, input().strip().split())
    graph[u].append((v, w))
start, end = map(int, input().strip().split())

def dijkstar(start, end):
    d = {v: float('inf') for v in graph}
    d[start] = 0
    pq = [(0, start)]

    while pq:
        dist, u = heapq.heappop(pq)
        if u == end:
            return d[u]
        for edge in graph[u]:
            v, w = edge
            if d[v] > d[u] + w:
                d[v] = d[u] + w
                heapq.heappush(pq, (d[v], v))
    

print(dijkstar(start, end))
