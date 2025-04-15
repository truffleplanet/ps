import sys

sys.setrecursionlimit(10**9)
def search(x, y):
    board[y][x] = -1
    for idx in range(4):
        cx = x + DX[idx]
        cy = y + DY[idx]
        if 0 <= cx < M and 0 <= cy < N and board[cy][cx] == 1:
            search(cx, cy)

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
    for x in range(M):
        for y in range(N):
            if board[y][x] == 1:
                search(x, y)
                count += 1
            else:
                board[y][x] = -1
    
    print(count)