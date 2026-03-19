import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int BH = H + X;
        int BW = W + Y;
        int[][] B = new int[BH][BW];
        for (int i = 0; i < BH; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < BW; ++j) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // A 만 포함되는 영역 
        int[][] A = new int[H][W];
        for (int i = 0; i < X; ++i) {
            for (int j = 0; j < Y; ++j) {
                A[i][j] = B[i][j];
            }
        }

        for (int i = X; i < H; i++) {
            for (int j = 0; j < Y; j++) {
                A[i][j] = B[i][j];
            }
        }

        for (int j = Y; j < W; j++) {
            for (int i = 0; i < X; i++) {
                A[i][j] = B[i][j];
            }
        }


        // 둘다 포함되는 영역
        for (int i = X; i < H; ++i) {
            for (int j = Y; j < W; ++j) {
                A[i][j] = B[i][j] - A[i - X][j - Y];
            }
        }

        // B만 포함되는 영역
        for (int i = H; i < BH; ++i) {
            for (int j = W; j < BW; ++j) {
                A[i - X][j - Y] = B[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < W; ++j) {
                sb.append(A[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}