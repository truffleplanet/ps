def draw_star(n):
    if n == 1:
        return ["*"]
    
    third = n // 3
    s = draw_star(third)
    s = s * 3
    top = third * 2

    for i in range(n):
        if third <= i < top:
            s[i] = s[i] + " " * third + s[i]
            
        else:
            s[i] *= 3
    
    return s

N = int(input())
print('\n'.join(draw_star(N)))
