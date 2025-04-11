def boom(s, p):
    stack = []
    part = len(p)
    p = list(p)

    for c in s:
        stack.append(c)
        if len(stack) >= part and stack[-part:] == p:
            for _ in range(part):
                stack.pop()
    if stack:
        print(''.join(stack))
    else:
        print("FRULA")
        
s = input()
p = input()

boom(s, p)