def power(n, r, c):
    if r == 0:
        return 1

    half = power(n, r // 2, c)

    if r % 2 == 0:
        return (half * half) % c
    else:
        return (half * half * n) % c


a, b, c = map(int, input().split())
print(power(a, b, c))
