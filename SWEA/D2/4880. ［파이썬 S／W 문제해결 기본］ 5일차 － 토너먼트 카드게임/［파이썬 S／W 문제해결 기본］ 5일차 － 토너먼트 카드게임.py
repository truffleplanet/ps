# 1: 가위, 2: 바위, 3: 보
# 1 - 3 승리
# 2 - 1 승리
# 3 - 2 승리 
def winner(a, b):
    if a[1] == b[1]:
        return a
    elif a[1] == 1 and b[1] == 3:
        return a
    elif a[1] == 2 and b[1] == 1:
        return a
    elif a[1]== 3 and b[1] == 2:
        return a
    else:
        return b

def game(arr, i, j):
    if i == j:
        return arr[i]
    mid = (i + j) // 2
    left = game(arr, i, mid)
    right = game(arr, mid+1, j)

    return winner(left, right) 

T = int(input())

for t in range(1, T+1):
    n = int(input())
    arr = list(map(int, input().split()))
    arr = [(i+1, arr[i]) for i in range(n)]
    ans = game(arr, 0, n-1)[0]
    print(f"#{t} {ans}")
