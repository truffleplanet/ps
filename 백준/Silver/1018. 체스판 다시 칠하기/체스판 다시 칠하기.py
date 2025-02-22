import sys

input_data = sys.stdin.read().splitlines()
h, w = map(int, input_data[0].split())
trans_table = str.maketrans("WB", "01")

board = [list(map(int, row.translate(trans_table))) for row in input_data[1:]]

white = [0, 1, 0, 1, 0, 1, 0, 1]
black = [1, 0, 1, 0, 1, 0, 1, 0]

white_board = [white if i % 2 == 0 else black for i in range(8)]
black_board = [black if i % 2 == 0 else white for i in range(8)]

min_repaint = float('inf')

for x in range(h - 8 + 1):
    for y in range(w - 8 + 1):
        repaint1 = 0
        repaint2 = 0
        for i in range(8):
            for j in range(8):
                if board[x + i][y + j] != white_board[i][j]:
                    repaint1 += 1
                if board[x + i][y + j] != black_board[i][j]:
                    repaint2 += 1
        min_repaint = min(min_repaint, repaint1, repaint2)

print(min_repaint)
