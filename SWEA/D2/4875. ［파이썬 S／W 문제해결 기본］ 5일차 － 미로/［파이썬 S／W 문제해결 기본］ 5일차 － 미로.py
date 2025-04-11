def can_go(maze, v, n, visited):
    candi = []
    # right
    if v[1] < n - 1 and maze[v[0]][v[1] + 1] != 1 and visited[v[0]][v[1]+1] == 0:
        candi.append((v[0], v[1] + 1))
    # down
    if v[0] < n - 1 and maze[v[0] + 1][v[1]] != 1 and visited[v[0] + 1][v[1]] == 0:
        candi.append((v[0] + 1, v[1]))

    # left
    if v[1] > 0 and maze[v[0]][v[1] - 1] != 1 and visited[v[0]][v[1] - 1] == 0:
        candi.append((v[0], v[1] - 1))
    # up
    if v[0] > 0 and maze[v[0] - 1][v[1]] != 1 and visited[v[0] - 1][v[1]] == 0:
        candi.append((v[0] - 1, v[1]))

    return candi

T = int(input())

for t in range(1, T+1):
    n = int(input())
    m = [[] for _ in range(n)]
    start = (0, 0)
    end = (0, 0)
    for i in range(n):
        row = list(input())
        for j, e in enumerate(row):
            e = int(e)
            if e == 2:
                start = (i, j)
            elif e == 3:
                end = (i, j)
            else:
                pass
            m[i].append(e)

    visited = [[0]*n for _ in range(n)]
    stack = [start]
    ans = 0
    while stack:
        node = stack.pop()
        visited[node[0]][node[1]] = 1
        cand = can_go(m, node, n, visited)
        if not cand:
            pass
        else:
            if end in cand:
                ans = 1
                break
            stack.extend(cand)
    print(f"#{t} {ans}")