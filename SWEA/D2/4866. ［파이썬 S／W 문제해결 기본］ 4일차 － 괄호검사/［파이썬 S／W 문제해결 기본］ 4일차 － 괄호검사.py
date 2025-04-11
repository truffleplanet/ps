def check_parentheses(expr: str):
    lefty = "{[("
    righty = "}])"
    stack = []
    for char in expr:
        if char in lefty:
            stack.append(char)
        elif char in righty:
            if not stack:
                return 0
            if righty.index(char) != lefty.index(stack.pop()):
                return 0
    if not stack:
        return 1
    else:
        return 0
    
T = int(input())

for t in range(1, T+1):
    expr = input()
    print(f"#{t} {check_parentheses(expr)}")