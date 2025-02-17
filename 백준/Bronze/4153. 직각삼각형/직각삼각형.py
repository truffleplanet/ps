a, b, c = 1, 1, 1

while True:
    a, b, c = map(int, input().split())
    if (a, b, c) == (0, 0, 0):
        break
    sorted_list = sorted([a, b, c], reverse=True)
    hypo = sorted_list[0] ** 2
    l_1 = sorted_list[1] ** 2
    l_2 = sorted_list[2] ** 2

    if hypo == (l_1 + l_2):
        print("right")
    else:
        print("wrong")
