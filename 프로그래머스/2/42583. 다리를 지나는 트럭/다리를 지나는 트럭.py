from collections import deque

def solution(bridge_length, weight, truck_weights):
    q = deque(truck_weights)
    bridge = deque()

    t = 0
    b_wsum = 0
    while q or bridge:
        t += 1
        if len(bridge) > 0:
            if (t - bridge[0][1]) % bridge_length == 0:
                    w, _ = bridge.popleft()
                    b_wsum -= w
        
        if len(q) > 0:
            if b_wsum + q[0] <= weight:
                car = q.popleft()
                b_wsum += car
                bridge.append((car, t))
                    
    answer = t
    return answer