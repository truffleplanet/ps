import sys
from collections import deque
input = sys.stdin.readline

H, W = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(H)]

start = None
for i in range(H):
    for j in range(W):
        if matrix[i][j] == 2:
            start = (i, j)
            break
    if start is not None:
        break

def bfs(start):
    DX = [0, 1, -1, 0]
    DY = [1, 0, 0, -1]
    q = deque([start])
    visited = [[False] * W for _ in range(H)]
    visited[start[0]][start[1]] = True
    matrix[start[0]][start[1]] = 0
    while q:
        cx, cy = q.popleft()
        for dx, dy in zip(DX, DY):
            nx = cx + dx
            ny = cy + dy
            if 0 <= nx < H and 0 <= ny < W and not visited[nx][ny]:
                visited[nx][ny] = True
                if matrix[nx][ny] == 0:
                    continue
                matrix[nx][ny] = matrix[cx][cy] + 1
                q.append((nx, ny))

    for i in range(H):
        for j in range(W):
            if matrix[i][j] == 1 and not visited[i][j]:
                matrix[i][j] = -1

bfs(start)

for row in matrix:
    print(" ".join(map(str, row)))
