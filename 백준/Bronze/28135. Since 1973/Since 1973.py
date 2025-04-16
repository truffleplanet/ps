N = int(input())

count = 0
for i in range(1, N + 1):
    count += 1
    if "50" in str(i):
        count += 1

if "50" in str(N):
    count -= 1

print(count)
