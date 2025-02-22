import sys
from collections import defaultdict

input_lines = sys.stdin.read().splitlines()

N, M = map(int, input_lines[0].split())
name_dict = defaultdict(lambda: 0)

never_heard = input_lines[1: N+1]
never_saw = input_lines[N+1:]
gokhalnoreut = []

for name in never_heard:
    name_dict[name] += 1

for name in never_saw:
    if name_dict[name]:
        gokhalnoreut.append(name)

gokhalnoreut.sort()
print(len(gokhalnoreut))
print(*gokhalnoreut, sep="\n")
