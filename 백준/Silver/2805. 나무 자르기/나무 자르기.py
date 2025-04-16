import sys

input = sys.stdin.readline
N, M = map(int, input().strip().split())
trees = list(map(int, input().strip().split()))

low = 0
high = max(trees)
res = 0
while low <= high:
    mid = (low + high) // 2
    if sum(tree - mid for tree in trees if tree > mid) >= M:
        res = mid
        low = mid + 1
    else:
        high = mid - 1

print(res)
