# 100, 1, 100
# 999, 1, 999
# 10000, 100, 10000

from itertools import permutations

T = int(input())

for t in range(1, T+1):
    n = int(input())
    board = [list(map(int, input().split())) for _ in range(n)]
    ans = float("inf")
    
    for p in permutations(range(n)):
        total = 0
        for row in range(n):
            total += board[row][p[row]]
            if total >= ans:
                break
        ans = min(ans, total)
    
    print(f"#{t} {ans}")