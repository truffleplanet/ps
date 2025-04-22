# dp[i] -> (a, b, c)
# dp[i][j] -> min(s[i][j] + dp[i-1][k]) k != j
import sys
input = sys.stdin.readline

N = int(input())
nodes = [tuple(map(int, input().strip().split())) for _ in range(N)]

dp = nodes[0]

for i in range(1, N):
    temp = [0, 0, 0]
    temp[0] = min(nodes[i][0] + dp[1], nodes[i][0] + dp[2])
    temp[1] = min(nodes[i][1] + dp[0], nodes[i][1] + dp[2])
    temp[2] = min(nodes[i][2] + dp[0], nodes[i][2] + dp[1])
    dp = (temp[0], temp[1], temp[2])

print(min(dp))
