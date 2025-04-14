N, M = map(int, input().split())

path = []
def backtrack(depth, N, M):
    if len(path) == M:
        print(" ".join(map(str, path)))
        return
    
    for i in range(1, N+1):
        path.append(i)
        backtrack(depth + 1, N, M)
        path.pop()

backtrack(0, N, M)
