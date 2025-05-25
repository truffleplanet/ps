from collections import Counter

T = int(input())

for _ in range(T):
    t = int(input())
    scores = list(map(int, input().split()))
    c = Counter(scores)
    freq = max(c.values())
    n = max([k for k, v in c.items() if v == freq])
    print(f"#{t} {n}")