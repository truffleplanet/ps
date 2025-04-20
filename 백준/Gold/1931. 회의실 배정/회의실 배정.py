import sys
input = sys.stdin.readline

N = int(input())
meetings = [tuple(map(int, input().strip().split())) for _ in range(N)]
meetings.sort(key=lambda x: (x[1], x[0]))

end = 0
count = 0
for t in meetings:
    if t[0] >= end:
        end = t[1]
        count += 1

print(count)
