import sys
input = sys.stdin.readline

N, k = map(int, input().rstrip().split())

coins = []
for _ in range(N):
    coins.append(int(input()))

count = 0
for i in range(N - 1, -1, -1):
    if k == 0:
        break
    if coins[i] <= k:
        r = k // coins[i]
        k -= r * coins[i]
        count += r

print(count)
