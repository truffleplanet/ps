N, M = map(int, input().split())

arr = [i for i in range(1, N+1)]

# subset
# O(N^2)
subsets = []
for i in range(2**N):
    subset = []
    for j in range(N):
        if i & (1 << j):
            subset.append(arr[j])
    if len(subset) == M:
        subsets.append(subset)

subsets.sort()
for subset in subsets:
    print(*subset)
