import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    commands = list(input().strip())
    n = int(input())
    arr = list(map(int, input().strip("[,], \n").replace(",", " ").split()))

    count_r = 0
    l_pop = 0
    r_pop = 0
    for c in commands:
        if c == "R":
            count_r += 1
        else:
            if count_r % 2 == 0:
                l_pop += 1
            else:
                r_pop += 1
    
    if l_pop + r_pop > n:
        print("error")
    else:
        arr = arr[l_pop: n - r_pop]

        if count_r % 2 == 0:
            print(f"[{','.join(map(str, arr))}]")
        else:
            print(f"[{','.join(map(str, arr[::-1]))}]")
