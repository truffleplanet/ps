import sys

input_data = sys.stdin.read().splitlines()
N, M = map(int, input_data[0].split())
trans_table = str.maketrans("WB", "01")

board = [int(row.translate(trans_table), base=2) for row in input_data[1:]]

white_mask = [(i % 2) * 0b10101010 + ((i + 1) % 2) * 0b01010101 for i in range(8)]
black_mask = [ x ^ 0b11111111 for x in white_mask]

min_repaint = float('inf')
for x in range(N - 8 + 1):
    for y in range(M - 8 + 1):
        repaint1 = 0
        repaint2 = 0
        for i in range(8):
            board_slice = (board[x+i] >> (M - 8 - y) & 0b11111111)
            repaint1 += bin(board_slice ^ white_mask[i]).count("1")
            repaint2 += bin(board_slice ^ black_mask[i]).count("1")

        min_repaint = min(min_repaint, repaint1, repaint2)

print(min_repaint)
