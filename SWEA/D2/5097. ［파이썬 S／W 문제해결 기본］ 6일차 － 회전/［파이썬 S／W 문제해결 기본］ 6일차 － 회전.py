T = int(input())

for t in range(1, T+1):
    n, m = map(int, input().split())
    arr = list(map(int, input().split()))
    idx = m % n
    print(f"#{t} {arr[idx]}")
    