T = int(input())

def add(x, y):
    return x + y

def sub(x, y):
    return x - y

def mult(x, y):
    return x * y

def div(x, y):
    return x // y

D = {
    "+": add,
    "-": sub,
    "*": mult,
    "/": div
}

def forth(s):
    stack = []
    for c in s:
        if c.isdecimal():
            stack.append(int(c))
        elif c == ".":
            return stack.pop() if len(stack) == 1 else "error"
        else:
            if len(stack) > 1:
                y = stack.pop()
                x = stack.pop()
                res = D[c](x, y)
                stack.append(res)
            else:
                return "error"

for t in range(1, T+1):
    expr = input().split()
    print(f"#{t} {forth(expr)}")
    