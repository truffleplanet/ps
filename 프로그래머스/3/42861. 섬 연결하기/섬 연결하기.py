import heapq
from collections import defaultdict
def solution(n, costs):
    graph = defaultdict(lambda: [])
    for u, v, w in costs:
        graph[u].append((v, w))
        graph[v].append((u, w))
        
    
    full_map = set(list(graph))
    visited = set()
    start = costs[0][0]
    visited.add(start)
    
    heap = []
    length = 0

    for v, w in graph[start]:
        heapq.heappush(heap, (w, start, v))
    
    while visited != full_map:
        w, _, v = heapq.heappop(heap)
        
        if v not in visited:
            visited.add(v)
            length += w
            
            for end, w in graph[v]:
                if end not in visited:
                    heapq.heappush(heap, (w, v, end))
    
    answer = length
    
    return answer