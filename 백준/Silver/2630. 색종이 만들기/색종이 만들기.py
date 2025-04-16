# N = 2^k (1 <= k <= 7) 2 128
import sys
from pprint import pprint

input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().strip().split())) for _ in range(N)]

blue = 0
white = 0

def choping(matrix):
    global blue
    global white
    n = len(matrix)
    # 전체가 0, 1 인지 체크
    is_white = True
    is_blue = True
    for row in matrix:
        if all(x == 0 for x in row):
            is_blue = False
        elif all(x == 1 for x in row):
            is_white = False
        else:
            is_blue = False
            is_white = False
            break
        
    if is_white == True:
        white += 1
        return
    elif is_blue == True:
        blue += 1
        return
    else:
        mid = n // 2
        quad_1 = [row[:mid] for row in matrix[:mid]]
        quad_2 = [row[mid:] for row in matrix[:mid]]
        quad_3 = [row[:mid] for row in matrix[mid:]]
        quad_4 = [row[mid:] for row in matrix[mid:]]
        choping(quad_1)
        choping(quad_2)
        choping(quad_3)
        choping(quad_4)

choping(board)
print(white)
print(blue)
