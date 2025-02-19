from collections import deque

def solution(bridge_length, weight, truck_weights):
    truck_q = deque(truck_weights)
    bridge_q = deque()

    t = 0
    curr_weight = 0
    while truck_q or bridge_q:
        t += 1
        if bridge_q and t - bridge_q[0][1] == bridge_length:
                w, _ = bridge_q.popleft()
                curr_weight -= w
        
        if truck_q and curr_weight + truck_q[0] <= weight:
                truck = truck_q.popleft()
                bridge_q.append((truck, t))
                curr_weight += truck
                    
    return t