import sys
input = sys.stdin.readline

# case1 . wL = 100, {99:990, 2:19, 98:978}
# 978 + 19 > 990
# 무게 대비 가치 접근은 배낭이 꽉 차지 않는 경우가 생길 수 있음

# p = {vals}
# w = {weights}
# v[i, w] = max(v[i-1, w], v[i-1, w-w[i]] + p[i])

N, W = map(int, input().rstrip().split())
items = [tuple(map(int, input().rstrip().split())) for _ in range(N)]
items.insert(0, (0, 0))

dp = [[0] * (W + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    weight, value = items[i]
    for w in range(1, W + 1):
        if w < weight:
            dp[i][w] = dp[i - 1][w]
        else:
            dp[i][w] = max(dp[i - 1][w], dp[i - 1][w - weight] + value)

print(dp[-1][-1])
