def solution(sizes):
    long_side = 0
    short_side = 0
    for size in sizes:
        ls = max(size)
        ss = min(size)
        if ls > long_side:
            long_side = ls
            
        if ss > short_side:
            short_side = ss
        
    answer = short_side * long_side
    return answer