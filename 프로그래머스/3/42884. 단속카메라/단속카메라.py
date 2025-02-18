# 차가 양방향인건지 단방향인건지..
# 일단 단방향으로 가정하고 풀겠음.

def solution(routes):
    routes.sort(key=lambda x: x[1])
    
    cam = 0
    last_cam = -30001
    
    for r in routes:
        start, end = r[0], r[1]
        
        if start > last_cam:
            cam += 1
            last_cam = end
    
    return cam