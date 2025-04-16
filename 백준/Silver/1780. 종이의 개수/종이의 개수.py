import sys

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
    s1 = seg(x, y, third)
    s2 = seg(x + third, y, third)
    s3 = seg(x + third * 2, y, third)
    s4 = seg(x, y + third, third)
    s5 = seg(x + third, y + third, third)
    s6 = seg(x + third * 2, y + third, third)
    s7 = seg(x, y + third * 2, third)
    s8 = seg(x + third, y + third * 2, third)
    s9 = seg(x + third * 2, y + third * 2, third)

    a = s1[0] + s2[0] + s3[0] + s4[0] + s5[0] + s6[0] + s7[0] + s8[0] + s9[0] 
    b = s1[1] + s2[1] + s3[1] + s4[1] + s5[1] + s6[1] + s7[1] + s8[1] + s9[1] 
    c = s1[2] + s2[2] + s3[2] + s4[2] + s5[2] + s6[2] + s7[2] + s8[2] + s9[2]

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
