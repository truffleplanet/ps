# white - 0, black - 1
# divide to 2 by 2
# compress
# N = 2^k (1 <= k <= 6)
import sys

input = sys.stdin.readline

N = int(input())
matrix = [list(map(int, input().strip())) for _ in range(N)]

def compress(x, y, n):
    mid = n // 2
    if n == 1:
        return str(matrix[y][x])

    q1 = compress(x, y, mid)
    q2 = compress(x + mid, y, mid)
    q3 = compress(x, y + mid, mid)
    q4 = compress(x + mid, y + mid, mid)

    if q1 == q2 == q3 == q4 and (q1 == "0" or q1 == "1"):
        return q1
    else:
        return "(" + q1 + q2 + q3 + q4 + ")"

            
print(compress(0, 0, N))
