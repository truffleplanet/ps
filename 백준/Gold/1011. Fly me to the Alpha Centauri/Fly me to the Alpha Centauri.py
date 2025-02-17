import math

T = int(input())

for _ in range(T):
    x, y = map(int, input().split())
    l = y - x

    k = int(math.sqrt(l))

    lower = k**2
    mid = lower + k

    if l == lower:
        print(2 * k - 1)

    elif l <= mid:
        print(2 * k)

    else:
        print(2 * k + 1)
