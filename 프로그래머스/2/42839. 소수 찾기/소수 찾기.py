from itertools import permutations

def is_prime(x):
    if x in [0, 1]:
        return 0
    
    for i in range(2, int(x**0.5)+1):
        if x % i == 0:
            return 0
    
    return 1

def solution(numbers):
    numbers = list(numbers)
    set_nums = set()
    count = 0
    for i in range(1, len(numbers)+1):
        permu = list(map("".join, permutations(numbers, i)))
        for k in permu:
            set_nums.add(int(k))
            
    for num in set_nums:
        print(num)
        count += is_prime(num)
        
    return count