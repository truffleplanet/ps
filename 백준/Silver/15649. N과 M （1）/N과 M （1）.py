N, M = map(int, input().split())

visited = [False] *  (N + 1)
path = []

def backtrack(depth, n, max_depth):
    if depth == max_depth:
        print(' '.join(map(str, path)))
        return
    
    for i in range(1, N+1):
        if not visited[i]:
            visited[i] = True
            path.append(i)
            backtrack(depth + 1, n, max_depth)
            visited[i] = False
            path.pop()

backtrack(0, N, M)
