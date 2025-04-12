def solution(N, number):
    dp = [set() for i in range(9)]
    dp[0] = 0
    dp[1] = {N}
    dp[2] = {N+N, N*11, N-N, N*N, N/N}

# dp[i] -> dp[i-1] +-*/ dp[1], dp[i-2] dp[2] ... 반대로 한번 더, int("N"*i) 
    for i in range(1, 9):
        dp[i].add(int(str(N) * i))
        for j in range(1, i):
            for x in dp[j]:
                for y in dp[i-j]:
                    dp[i].add(x + y)
                    dp[i].add(x - y)
                    dp[i].add(x * y)
                    if y != 0:
                        dp[i].add(x // y)
        if number in dp[i]:
            return i
    
    return -1