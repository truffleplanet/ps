N = int(input())

import sys

words = []
for _ in range(N):
    word = sys.stdin.readline().strip()
    words.append(word)

words = set(words)
words = list(words)

words.sort(key=lambda x: (len(x), x))
print(*words, sep="\n")
