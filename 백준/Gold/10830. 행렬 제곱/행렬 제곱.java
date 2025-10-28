import java.util.*;
import java.lang.*;
import java.io.*;


// The main method must be in a class named "Main".
class Main {

    static int[][] matmul(int[][] x, int[][] y) {
        int N = x.length;
        int M = y[0].length;
        
        int[][] out = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    out[i][j] = (out[i][j] + (x[i][k] * y[k][j])) % 1000;
                }
            }
        }

        return out;
    }

    static int[][] unitMatrix(int N) {
        int[][] out = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    out[i][j] = 1;
                }
            }
        }
        return out;
    }


    static int[][] fastExp(int[][] base, long exp) {
        int[][] result = unitMatrix(base.length);
        int[][] x = base;
        long y = exp;

        while (y > 0) {
            if (y % 2 != 0) {
                result = matmul(result, x);
            }
            x = matmul(x, x);
            y = y / 2;
        }
        return result;
    }

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long E = Long.parseLong(st.nextToken());

        int[][] base = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                base[i][j] = Integer.parseInt(st.nextToken().trim());
            }
        }

        int[][] out = fastExp(base, E);

        for (int[] row : out) {
            for (int v : row) {
                sb.append(v).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
     }
}