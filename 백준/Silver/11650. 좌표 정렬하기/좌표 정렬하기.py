N = int(input())

points = []
for _ in range(N):
    x, y = map(int, (input().split()))
    points.append((x, y))

points.sort(key=lambda x: (x[0], x[1]))

for p in points:
    print(p[0], p[1])
