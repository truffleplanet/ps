# L, D, B, P {}
import sys

input = sys.stdin.readline

left = list(input().strip())
M = int(input())

right = []
for _ in range(M):
    command = input().strip().split()
    if len(command) > 1 and command[0] == "P":
        left.append(command[1])
    elif command[0] == "L" and left:
        right.append(left.pop())
    elif command[0] == "D" and right:
        left.append(right.pop())
    elif command[0] == "B" and left:
        left.pop()
    else:
        pass

for c in right[::-1]:
    left.append(c)

print("".join(left))
