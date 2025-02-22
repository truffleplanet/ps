import sys

def count_fibo(x):
    fibo_list = [(1, 0), (0, 1)]
    
    if x > 1:
        for i in range(2, x+1):
            zeros = fibo_list[i-2][0] + fibo_list[i-1][0]
            ones = fibo_list[i-2][1] + fibo_list[i-1][1]
            fibo_list.append((zeros, ones))
        
    return fibo_list[x]


input_lines = list(map(int, sys.stdin.read().splitlines()))

for k in input_lines[1:]:
    print(*count_fibo(k), sep=" ")
