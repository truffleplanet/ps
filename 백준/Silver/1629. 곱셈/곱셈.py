def power(n, r, c):
    half = r // 2

    if r == 0:
        return 1

    if r % 2 == 0:
        return (power(n, half, c) ** 2) % c
    else:
        return ((power(n, half, c) ** 2) * n) % c


a, b, c = map(int, input().split())
print(power(a, b, c))
