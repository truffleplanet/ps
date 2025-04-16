import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().strip().split())) for _ in range(N)]

def seg(x, y, n):
    if n == 1:
        if board[y][x] == -1:
            return (1, 0, 0)
        elif board[y][x] == 0:
            return (0, 1, 0)
        else:
            return (0, 0, 1)
    
    third = n // 3
    subs = [
        seg(x + dx * third, y + dy * third, third) 
        for dy in range(3) 
        for dx in range(3)
    ]
    a, b, c = [sum(x) for x in zip(*subs)]

    if (a, b, c).count(0) == 2:
        if a == 9:
            return (1, 0, 0)
        elif b == 9:
            return (0, 1, 0)
        elif c == 9:
            return (0, 0, 1)
    else:
        return (a, b, c)

ans = seg(0, 0, N)

for a in ans:
    print(a)
    