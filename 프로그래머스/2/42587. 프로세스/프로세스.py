from collections import deque

def solution(priorities, location):
    q = deque([(idx, n) for idx, n in enumerate(priorities)])
    visit = {}
    rank = 1
    while len(visit) < len(priorities):
        k = q.popleft()
        if len(q) == 0:
            visit[k[0]] = rank
            break
            
        if k[1] >= max(q, key=lambda x: x[1])[1]:
            visit[k[0]] = rank
            rank += 1
        else:
            q.append(k)
    
    answer = visit[location]
    return answer