from collections import deque

def solution(progresses, speeds):
    answer = []
    progresses = deque(progresses)
    speeds = deque(speeds)
    
    day = 0
    count = 0
    while progresses:
        progress = progresses.popleft()
        speed = speeds.popleft()
        while progress + (speed*day) < 100:
            if count != 0:
                answer.append(count)
                count = 0
            day +=1
            
        count += 1
        
    if count != 0:
        answer.append(count)

    return answer