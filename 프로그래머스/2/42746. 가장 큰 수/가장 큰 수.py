def solution(numbers):
    numbers = list(map(str, numbers))
    
    
    def sort_func(x):
        x *= 4
        return x[:4]
    
    numbers.sort(key=sort_func, reverse=True)
    
    answer = ''.join(numbers)
    answer = str(int(answer))
    
    return answer