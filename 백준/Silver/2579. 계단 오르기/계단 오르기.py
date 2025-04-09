N = int(input())

stair = []
for _ in range(N):
    stair.append(int(input()))

dp = [0] * N

dp[0] = stair[0]

if len(stair) > 1:
    dp[1] = stair[0] + stair[1]

if len(stair) > 2:    
    dp[2] = max(stair[0] + stair[2], stair[1] + stair[2])

if len(stair) > 3:
    for i in range(3, len(stair)):
        dp[i] = max(dp[i-2] + stair[i], dp[i-3] + stair[i-1] + stair[i])

print(dp[-1])
