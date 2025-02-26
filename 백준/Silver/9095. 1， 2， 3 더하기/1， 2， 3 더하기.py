# 1, 2, 3의 합으로 쪼개기
# TODO 각 케이스별 순열 구하기
# 먼저 모든 순열을 구하기 위한 방법을 고안해야 함.
# one, two, three를 카운트하는 변수 생성
# one = n
# 아래를 반복
# 가능하면 three 추가, one -= 3
# 가능하면 three 추가 반복.
# three 추가 불가능해지면 three = 0으로 두고
# two 추가, one -=1
# 이걸 끝까지 반복하면 2가 기준이 되어 
# 2가 0개인 경우부터 최대인 경우까지
# 1, 3의 가능한 비율을 전부 탐색하게 됨.
# 매번 확정된 연산마다, count = one + two + three
# (count, one, two, three)를 저장해두기
# TODO 케이스별 경우의 수 계산
# count! / one! * two! * three!
import sys
from math import factorial

def solution(k):
    one = k
    two = 0
    three = 0
    arr = []
    count = one + two + three
    answer = factorial(count) // (factorial(one) * factorial(two) * factorial(three))
    while two * 2 < k - 1: # 2의 갯수가 최대일 때 그 값은 1의 최대 갯수 or 1만큼 작을 것임. base case.

        # 가능한 만큼 3 넣어보기
        while one >= 3:
            three += 1
            one -= 3
            count = one + two + three
            answer += factorial(count) // (factorial(one) * factorial(two) * factorial(three))
        
        # 3을 전부 빼고 초기화 
        one += 3 * three
        three = 0
        if one >= 2:
            one -= 2
            two += 1
            count = one + two + three
            answer += factorial(count) // (factorial(one) * factorial(two) * factorial(three))

    return answer


data = sys.stdin.read().splitlines()
T = data[0]

for n in data[1:]:
    print(solution(int(n)))
