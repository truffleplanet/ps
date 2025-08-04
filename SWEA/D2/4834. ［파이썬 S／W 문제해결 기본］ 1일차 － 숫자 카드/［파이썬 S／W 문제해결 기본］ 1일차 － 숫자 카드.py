
T = int(input())

for t in range(1, T + 1):
    lst = [0] * 10
    N = int(input())
    line = input()
    
    for c in line:
        lst[int(c)]+= 1
    
    max_count = 0
    max_value = 0
    for i in range(10):
        if lst[i] >= max_count:
            max_count = lst[i]
            max_value = i
    print(f"#{t} {max_value} {max_count}")