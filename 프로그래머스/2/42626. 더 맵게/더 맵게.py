from heapq import heapify, heappop, heappush

def solution(scoville, K):
    answer = 0
    heapify(scoville)
    while scoville[0] < K:
        if len(scoville) < 2:
            return -1
        a = heappop(scoville)
        b = heappop(scoville)
        new = a + (2 * b)
        heappush(scoville, new)
        answer += 1
        
    return answer