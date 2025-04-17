# i = ord(Char) - 65
a = [3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1]

arr = [a[ord(c) - 65] for c in list(input())]
ans = sum(arr) % 10

if ans % 2 == 0:
    print("You're the winner?")
else:
    print("I'm a winner!")
    