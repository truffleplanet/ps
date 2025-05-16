T = int(input())

for t in range(1, T + 1):
    N, M = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    
    end_pos = N - M + 1
    # res = [[0 for _ in range(end_pos)] for _ in range(end_pos)]
    ans = float("-inf")
    for i in range(end_pos):
        for j in range(end_pos):
            sum_val = 0
            for k_h in range(M):
                for k_w in range(M):
                    sum_val += matrix[i + k_h][j + k_w]
            if sum_val > ans:
                ans = sum_val
            
    print(f"#{t} {ans}")
