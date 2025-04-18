# Xi의 좌표를 압축한 결과는
# 그 좌표보다 작은 서로다른 좌표의 개수
# sorting해서 푸는게 편하지만 출력형식을 맞춰야하니
# d = {x: 압축결과}에 답 저장 후 출력

N = int(input())
arr = list(map(int, input().split()))
sorted_set = sorted(set(arr))

d = {x: i for i, x in enumerate(sorted_set)}

print(' '.join(str(d[key]) for key in arr))
