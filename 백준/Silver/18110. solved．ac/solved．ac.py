import math

n = int(input())

scores = []
for _ in range(n):
    score = int(input())
    scores.append(score)

if n == 0:
    print("0")

else:
    scores.sort()

    cut_off = math.floor((n * 15 / 100) + 0.5)
    sub_arr = scores[cut_off : n - cut_off]
    mean = sum(sub_arr) / len(sub_arr)
    mean = math.floor(mean + 0.5)

    print(mean)
    