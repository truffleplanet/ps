T = int(input())

for _ in range(T):
    n, m = map(int, input().split())
    a = map(int, input().split())
    b = map(int, input().split())
    set_a = 0
    set_b = 0
    for c in a:
        set_a |= 1 << c
        
    for c in b:
        set_b |= 1 << c
        
    if set_a == set_b:
        print("=")
    elif (set_a | set_b) == set_b:
        print("<")
    elif (set_a | set_b) == set_a:
        print(">")
    else:
        print("?")
    