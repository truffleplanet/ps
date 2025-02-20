class Stack:
    def __init__(self):
        self.data = []
        self.length = 0

    def push(self, x):
        self.data.append(x)
        self.length += 1

    def pop(self):
        if self.length == 0:
            print(-1)
        else:
            self.length -= 1
            print(self.data.pop())

    def size(self):
        print(self.length)

    def empty(self):
        if self.length == 0:
            print(1)
        else:
            print(0)

    def top(self):
        if self.length == 0:
            print(-1)
        else:
            print(self.data[-1])


import sys

N = int(input())
stack = Stack()

for _ in range(N):
    command = sys.stdin.readline().strip().split()
    if len(command) > 1:
        com, x = command[0], int(command[1])
        method = getattr(stack, com)
        method(x)
    else:
        method = getattr(stack, command[0])
        method()
