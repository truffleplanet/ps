def solution(clothes):

    d = {}

    for c in clothes:
        if c[1] not in d.keys():
            d[c[1]] = 1    
            
        else:
            d[c[1]] += 1
    
    answer = 1
    # 하나도 안 입는 경우를 카테고리별 +1 해주고
    for _, v in d.items():
        answer *= (v + 1)
    
    # 아무것도 안 입는 경우를 뺀다.
    answer -= 1
    
    return answer