import sys
input = sys.stdin.readline

N, M = map(int, input().rstrip().split())
table = [list(map(int, input().rstrip().split())) for _ in range(N)]

s = [[0] * (N + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    for j in range(1, N  + 1):
        s[i][j] = table[i - 1][j - 1] + s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1]

for _ in range(M):
    x1, y1, x2, y2 = map(int, input().rstrip().split())
    res = s[x2][y2] - s[x1 - 1][y2] - s[x2][y1 - 1] + s[x1 - 1][y1 - 1]    
    print(res)
