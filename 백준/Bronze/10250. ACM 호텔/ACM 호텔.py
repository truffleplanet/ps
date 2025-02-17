T = int(input())

for _ in range(T):
    h, w, n = map(int, input().split())

    a, b = divmod(n, h)

    a += 1
    if b == 0:
        b = h
        a -= 1

    print("{:d}{:02d}".format(b, a))
