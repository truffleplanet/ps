import sys
from collections import deque
input = sys.stdin.readline

DX = [-1, 1, 0, 0]
DY = [0, 0, 1, -1]

W, H = map(int, input().strip().split())
matrix = [list(map(int, input().strip().split())) for _ in range(H)]

def bfs():
    count = [0, 0]
    q = deque()
    for y in range(H):
        for x in range(W):
            if matrix[y][x] == 1:
                q.append((y, x))
            elif matrix[y][x] == 0:
                count[0] += 1

    day = 0
    while q:
        if count[0] == count[1]:
            return day

        for _ in range(len(q)):
            cy, cx = q.popleft()

            for dx, dy in zip(DX, DY):
                nx = cx + dx
                ny = cy + dy
                if 0 <= ny < H and 0 <= nx < W:
                    if matrix[ny][nx] == 0:
                        matrix[ny][nx] = 1
                        count[1] += 1
                        q.append((ny, nx))
                    else:
                        continue
        day += 1

    return -1


print(bfs())
    