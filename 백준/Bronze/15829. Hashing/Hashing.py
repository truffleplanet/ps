r = 31
M = 1234567901

L = int(input())
s = input()
h = 0
power = 1
for i in range(L):
    k = ord(s[i]) - 96
    h = (h + (k * power)) % M
    power = (power * r) % M

print(h)
