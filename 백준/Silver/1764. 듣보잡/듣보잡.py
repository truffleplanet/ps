import sys

input_lines = sys.stdin.read().splitlines()

N, M = map(int, input_lines[0].split())

never_heard = set(input_lines[1: N+1])
never_saw = set(input_lines[N+1:])

gokhalnoreut = sorted(never_heard & never_saw)
print(len(gokhalnoreut))
print(*gokhalnoreut, sep="\n")
