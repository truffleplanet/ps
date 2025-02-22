def solution(brown, yellow):
    answer = []
    # brown > ((col+2) + (row+2)) - yellow
    
    # yellow > col * row
    
    for col in range(1, yellow+1):
        if yellow % col != 0:
            continue
            
        row = yellow // col
            
        if brown == ((col+2) * (row+2)) - yellow:
            answer.append(max([col+2, row+2]))
            answer.append(min([col+2, row+2]))
            break

    return answer