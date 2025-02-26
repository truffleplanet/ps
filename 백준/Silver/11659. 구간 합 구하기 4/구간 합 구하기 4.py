import sys

data = sys.stdin.read().splitlines()
arr = list(map(int, data[1].split()))

cum_sum = [0] * len(arr)
sum = 0
for i in range(len(arr)):
    sum += arr[i]
    cum_sum[i] = sum

result = []
for line in data[2:]:
    i, j = map(int, line.split())
    # 첫번째 수가 0번 인덱스이므로
    i -= 1
    j -= 1
    # i <= j
    if i == 0:
        result.append(str(cum_sum[j]))
    else:
        result.append(str(cum_sum[j] - cum_sum[i-1]))

sys.stdout.write("\n".join(result) + "\n")