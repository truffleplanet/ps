import sys

data = sys.stdin.read().splitlines()

for line in data[:-1]:
    b, n = map(int, line.split())
    approx = round(b ** (1/n))
    l = approx - 1
    r = approx + 1
    lst = [(abs(b - l ** n), l), (abs(b - approx ** n), approx), (abs(b - r ** n), r)]
    ans = min(lst)
    print(ans[1])
