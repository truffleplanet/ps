N, M = map(int, input().split())
arr = sorted((map(int, input().split())))

visited = [False] * N
path = []
path_set = set()
# visited만으로는 같은 수열이 관리가 안됨.

def backtrack():
    if len(path) == M:
        s = " ".join(map(str, path))
        if s not in path_set:
            path_set.add(s)
            print(s)
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            path.append(arr[i])
            backtrack()
            visited[i] = False
            path.pop()

backtrack()
