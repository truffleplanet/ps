N, M = map(int, input().split())

# 비 내림차순
path = []
def backtrack():
    if len(path) == M:
        print(" ".join(map(str, path)))
        return
    
    for i in range(1, N+1):
        if not path or i >= path[-1]:
            path.append(i) 
            backtrack()
            path.pop()

backtrack()
