T = int(input())

for t in range(1, T+1):
    stack = list(input())
    is_on = True
    temp = stack.pop()
    new = [temp]
    while stack:
        c = stack.pop()
        if new and c == new[-1]:
            new.pop()
        else:
            new.append(c)
            
    print(f"#{t} {len(new)}")
    