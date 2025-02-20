a, b = map(int, input().split())


def gcd(x, y):
    if x % y == 0:
        return y
    else:
        return gcd(y, x % y)


ab_gcd = gcd(a, b)
ab_lcm = (a * b) // ab_gcd
print(ab_gcd)
print(ab_lcm)
