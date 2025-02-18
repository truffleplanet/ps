import sys

N = int(input())
N_list = sys.stdin.readline().strip().split()

M = int(input())
M_list = sys.stdin.readline().strip().split()

N_set = set(N_list)

for m in M_list:
    if m in N_set:
        print(1)
    else:
        print(0)
