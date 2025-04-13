T = int(input())

# 최종 거리가 최대 거리가 아닌 경우를 고려해야 함.
# LRLRL?R 
# # LRLRL?RR 
# LL??RR??LL
# LRLRL?RR

# dp[i] = (min_pos, max_pos)


for t in range(1, T+1):
    s = input()
    x = 0
    min_pos = float("inf")
    max_pos = float("-inf")
    dp = [[0, 0]] * (len(s) + 1)
    for i, c in enumerate(s, start=1):
        min_po, max_po = dp[i-1][0], dp[i-1][1]
        if c == "L":
            min_po -= 1
            max_po -= 1
        elif c == "R":
            max_po += 1
            min_po += 1
        else:
            min_po -= 1
            max_po += 1
        dp[i][0], dp[i][1] = min_po, max_po
        if min_po < min_pos:
            min_pos = min_po
        if max_po > max_pos:
            max_pos = max_po

    print(max(abs(min_pos), abs(max_pos)))
