N, M = map(int, input().split())
arr = sorted(list(map(int, input().split())))
# 사전 순 증가 출력

visited = [False] * N
path = []
def backtrack():
    if len(path) == M:
        print(" ".join(map(str, path)))
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            path.append(arr[i])
            backtrack()
            visited[i] = False
            path.pop()

backtrack()
