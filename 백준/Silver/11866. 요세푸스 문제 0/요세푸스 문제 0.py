from collections import deque

N, K = map(int, input().split())


def yosef(n, k):
    arr = []
    queue = deque([i for i in range(1, n + 1)])
    count = 0
    while True:
        count += 1
        e = queue.popleft()

        if not queue:
            arr.append(str(e))
            break

        if count == k:
            arr.append(str(e))
            count = 0

        else:
            queue.append(e)

    return arr

print("<{}>".format(", ".join(yosef(N, K))))
