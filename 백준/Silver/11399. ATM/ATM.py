import sys

N = int(sys.stdin.readline())
M = list(map(int, (sys.stdin.readline().split())))
M.sort()

min_waitng = 0
for i in range(N):
    n = N - i
    min_waitng += n * M[i]

print(min_waitng)
