# K자리 자연수 N에서
# 무작위 K개의 자연수를 뺼샘하여 P를 얻고
# P의 분해합이 N이면 생성자
# idea. 최대한 빼면서 될 때까지 한다.
# 되는 순간이 정답.

def decompose_sum(n):
    for c in str(n):
        n += int(c)
    return n


N = input()

low = len(N)
k = low * 9
N = int(N)

no_ans = True
while k >= low:
    p = N - k
    if p > 0 and decompose_sum(p) == N:
        print(p)
        no_ans = False
        break
    else:
        k -= 1

if no_ans:
    print(0)
