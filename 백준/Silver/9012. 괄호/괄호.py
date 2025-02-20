import sys

N = int(input())

for _ in range(N):
    stack = []
    par_counter = {"(": 0, ")": 0}

    stack = list(sys.stdin.readline().strip())

    while stack:
        par = stack.pop()
        par_counter[par] += 1

        if par_counter["("] > par_counter[")"]:
            break

    if par_counter["("] == par_counter[")"]:
        print("YES")
    else:
        print("NO")
