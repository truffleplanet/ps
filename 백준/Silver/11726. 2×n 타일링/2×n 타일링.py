# 2 x n
# 1 x 2은 2개 단위에서만 사용 가능하고
# 2 x 1는 어디서나 사용이 가능하다.
# 2 x 1 --> 1
# 2 x 2 --> 2
# 2 x 3 --> 3
# 2 x 4 --> 5
# 2 x 5 --> (1만 있는 경우 5!/5! + 2하나 있는 경우 4!/3!, 2두개 있는 경우 - 3!/2! ) - 1+4+3 = 8
# 2 x 6 0-1  1-5 2-6  3
# 방법 1. 가능한 1 x 2 쌍 구하고
# 한 개씩 추가하며 순열로 생각하여 갯수 구할 수 있음.

from math import factorial

n = int(input())

one = n
two = 0

outcomes = 1 # 1*2 만 있는 경우
while one > 1:
    two += 1
    one -= 2
    outcomes += factorial(two+one) // (factorial(two)*factorial(one))

print(outcomes % 10007)
