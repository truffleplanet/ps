import sys
from collections import defaultdict

read = sys.stdin.readline

n = int(read())
connection = int(read())

network = defaultdict(list)
for _ in range(connection):
    u, v = map(int, read().split())
    network[u].append(v)
    network[v].append(u)

visited = [False] * (n+1)
stack = [1]

while stack:
    u = stack.pop()
    if visited[u] is True:
        continue

    visited[u] = True

    for v in network[u]:
        if visited[v] is False:
            stack.append(v)

print(sum(visited)-1)
