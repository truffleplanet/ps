N, M = map(int, input().split())
arr = sorted((map(int, input().split())))

visited = [False] * N
path = []
path_set = set()

def backtrack(n):
    if len(path) == M:
        s = " ".join(map(str, path))
        if s not in path_set:
            path_set.add(s)
            print(s)
        return
    
    for i in range(n, N):
        if not visited[i]:
            visited[i] = True
            path.append(arr[i])
            backtrack(i + 1)
            visited[i] = False
            path.pop()

backtrack(0)
