N, M = map(int, input().split())
arr = sorted((map(int, input().split())))

path = []

def backtrack():
    if len(path) == M:
        print(" ".join(map(str, path)))
        return
    
    prev = -1
    for i in range(N):
        if arr[i] == prev:
            continue
        path.append(arr[i])
        backtrack()
        path.pop()
        prev = arr[i]

backtrack()
