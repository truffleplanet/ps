def palindrome(seq):
    if seq == seq[::-1]:
        return True
    else:
        return False

def search_all(arr, n, m):
    for i in range(n):
        for j in range(n - m + 1):
            cand = arr[i][j : j + m]
            if palindrome(cand):
                return cand
    
    for i in range(n):
        for j in range(n - m + 1):
            cand = [arr[j + k][i] for k in range(m)] 
            if palindrome(cand):
                return cand


T = int(input())

for t in range(1, T+1):
    n, m = map(int, input().split())
    arr =[]
    for _ in range(n):
        sub = list(input())
        arr.append(sub)
    
    ans = search_all(arr, n, m)
    print(f"#{t} ", end="")
    print(*ans, sep="")
    