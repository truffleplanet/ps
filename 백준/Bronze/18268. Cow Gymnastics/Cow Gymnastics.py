K, N = map(int, input().split())
matrix = [input().split() for _ in range(K)]


# (4 1) (4 2) (4 3) (1 2) (1 3) (2 3)
sessions = []
for row in matrix:
    temp = set()
    for i in range(N - 1):
        for j in range(i+1, N):
            temp.add((row[i], row[j]))
    sessions.append(temp)

ans_set = sessions[0]
for session in sessions[1:]:
    ans_set &= session

print(len(ans_set))