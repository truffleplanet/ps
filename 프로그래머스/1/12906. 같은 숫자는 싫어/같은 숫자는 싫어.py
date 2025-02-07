def solution(arr):
    answer = [-1]
    
    for ele in arr:
        if ele != answer[-1]:
            answer.append(ele)
    
    return answer[1:]