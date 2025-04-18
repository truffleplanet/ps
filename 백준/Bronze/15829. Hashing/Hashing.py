r = 31
M = 1234567901

n = int(input())
s = input()
h = 0
for i, c in enumerate(s):
    h += (ord(c) - 96) * (r ** i)
h %= M
print(h)