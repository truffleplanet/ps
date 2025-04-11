from math import factorial

T = int(input())

for t in range(1, T+1):
    n = int(input()) // 10
    
    small = n
    large = 0
    ans = 1
    while small > 1:
        small -= 2
        large += 1
        permu = factorial(small + large) // (factorial(small) * factorial(large))
        ans += permu * (2**large)

    print(f"#{t} {ans}")