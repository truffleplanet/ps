import sys

N = int(sys.stdin.readline())

data = 0
for _ in range(N):
    line = sys.stdin.readline().split()

    if len(line) == 1:
        command = line[0]
    else:
        command, arg = line[0], int(line[1])

    if command == "add":
        data |= (1 << arg)
    
    if command == "remove":
        data &= ~(1 << arg)

    if command == "check":
        print((data >> arg) & 1)

    if command == "toggle":
        data ^= (1 << arg)
    
    if command == "all":
        data = (1 << 21) - 1

    if command == "empty":
        data = 0
