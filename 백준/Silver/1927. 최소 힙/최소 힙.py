import sys
import heapq
data = sys.stdin.read().splitlines()

heap = []
for n in data[1:]:
    n = int(n)
    if n == 0:
        if not heap:
            print("0")
        else:
            print(heapq.heappop(heap))
    else:
        heapq.heappush(heap, n)
