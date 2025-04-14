N, M = map(int, input().split())
arr = sorted(list(map(int, input().split())))

path = []


def backtrack():
    if len(path) == M:
        print(" ".join(map(str, path)))
        return

    for i in range(N):
        path.append(arr[i])
        backtrack()
        path.pop()

        
backtrack()
