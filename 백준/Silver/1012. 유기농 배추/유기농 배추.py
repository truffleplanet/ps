import sys

def search(x, y):
    board[y][x] = -1
    stack = [(x, y)]

    while stack:
        cx, cy = stack.pop()
        for idx in range(4):
            nx = cx + DX[idx]
            ny = cy + DY[idx]
            if 0 <= nx < M and 0 <= ny < N and board[ny][nx] == 1:
                board[ny][nx] = -1
                stack.append((nx, ny))

input = sys.stdin.readline
DX = [1, 0, -1, 0]
DY = [0, 1, 0, -1] 

T = int(input())

for _ in range(T):
    M, N, K = map(int, input().strip().split())
    board = [[0] * M for _ in range(N)]
    for _ in range(K):
        x, y = map(int, input().strip().split())
        board[y][x] = 1
    
    count = 0
    for y in range(N):
        for x in range(M):
            if board[y][x] == 1:
                search(x, y)
                count += 1
    
    print(count)
    