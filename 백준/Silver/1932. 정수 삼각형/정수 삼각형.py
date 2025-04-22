# dp[i+1][0] -> dp[i][0] + s[i+1][0]
# dp[i+1][-1] -> dp[i][-1] + s[i+1][-1]
# dp[i+1][j] -> max(dp[i][j-1] + s[i+1][j], dp[i][j] +  s[i+1][j])

import sys
input = sys.stdin.readline

N = int(input())
tri = [list(map(int, input().strip().split())) for _ in range(N)]

for i in range(1, N):
    for j in range(i+1): # 0..len(tri[i])
        if j == 0:
            tri[i][j] += tri[i-1][j]
        elif j == i:
            tri[i][j] += tri[i-1][j-1]
        else:
            tri[i][j] = max(tri[i-1][j-1] + tri[i][j], tri[i-1][j] + tri[i][j])

print(max(tri[-1]))
