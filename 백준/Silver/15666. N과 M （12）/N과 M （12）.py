N, M = map(int, input().split())
arr = sorted((map(int, input().split())))

path = []

def backtrack(n):
    if len(path) == M:
        print(" ".join(map(str, path)))
        return
    
    prev = -1
    for i in range(n, N):
        if arr[i] == prev:
            continue
        path.append(arr[i])
        backtrack(i)
        path.pop()
        prev = arr[i]

backtrack(0)
