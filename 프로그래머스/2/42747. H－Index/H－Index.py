def solution(citations):
    answer = 0
    
    citations.sort(reverse=True)
    
    for idx, cite in enumerate(citations):
        if idx+1 > cite:
            return idx
        
    return len(citations)