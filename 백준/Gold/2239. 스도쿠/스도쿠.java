import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static boolean[][] isInRow;
    static boolean[][] isInCol;
    static boolean[][][] isInBucket;
    static int[][] board;
    static List<Point> zeros;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        isInRow = new boolean[9][10];
        isInCol = new boolean[9][10];
        isInBucket = new boolean[3][3][10];
        board = new int[9][9];

        zeros = new ArrayList<>();

        for (int r = 0; r < 9; r++) {
            char[] line = br.readLine().toCharArray();
            for (int c = 0; c < 9; c++) {
                int v = line[c] - '0';
                board[r][c] = v;
                isInRow[r][v] = true;
                isInCol[c][v] = true;
                isInBucket[r / 3][c / 3][v] = true;

                if (v == 0) {
                    zeros.add(new Point(r, c));
                }
            }
        }

        backtrack(0);

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                sb.append(board[r][c]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static boolean backtrack(int depth) {
        if (depth == zeros.size()) {
            return true;
        }

        Point cur = zeros.get(depth);
        int br = cur.r / 3;
        int bc = cur.c / 3;

        for (int k = 1; k <= 9; k++) {
            if (isInRow[cur.r][k] || isInCol[cur.c][k] || isInBucket[br][bc][k])
                continue;
            isInRow[cur.r][k] = true;
            isInCol[cur.c][k] = true;
            isInBucket[br][bc][k] = true;
            board[cur.r][cur.c] = k;
            if (!backtrack(depth + 1)) {
                isInRow[cur.r][k] = false;
                isInCol[cur.c][k] = false;
                isInBucket[br][bc][k] = false;
                board[cur.r][cur.c] = 0;
            } else {
                return true;
            }
        }

        return false;
    } 
}