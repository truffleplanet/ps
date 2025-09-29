from collections import deque

N, K = map(int, input().split())

visited = [False] * 200001
visited[N] = True
dq = deque([(N, 0)])

while dq:
    x, t = dq.popleft()
    if x == K:
        print(t)
        break
    
    if 2 * x <= 200000 and not visited[2 * x]:
        visited[2 * x] = True
        dq.appendleft((x * 2, t))

    if 0 <= x - 1 <= 200000 and not visited[x - 1]:
        visited[x - 1] = True
        dq.append((x - 1, t + 1))

        
    if  x + 1 <= 200000 and not visited[x + 1]:
        visited[x + 1] = True
        dq.append((x + 1, t + 1))

