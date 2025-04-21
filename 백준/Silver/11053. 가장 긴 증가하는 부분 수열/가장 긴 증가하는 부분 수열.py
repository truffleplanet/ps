import sys
input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().strip().split()))

dp = [1] * N
targ = 0
for i in range(1, N):
    for j in range(i):
        if arr[j] < arr[i] and dp[i] < 1 + dp[j]:
            dp[i] = dp[j] + 1
        if dp[targ] < dp[i]:
            targ = i

print(dp[targ])
