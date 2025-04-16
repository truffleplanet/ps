N = int(input())

for _ in range(N):
    s = input()
    if s and s[-1] != ".":
        s += "."
    print(s)