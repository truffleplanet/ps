N, M = map(int, input().split())
arr = sorted(list(map(int, input().split())))

path = []
def backtrack(n):
    if len(path) == M:
        print(" ".join(map(str, path)))
        return
    
    for i in range(n, N):
        path.append(arr[i])
        backtrack(i)
        path.pop()

backtrack(0)
