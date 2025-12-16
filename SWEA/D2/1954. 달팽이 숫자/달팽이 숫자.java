import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {

    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            int maxVal = N * N;

            int r = 0;
            int c = 0;
            int val = 1;
            int d = 0;
            
            while (val <= maxVal) {
                arr[r][c] = val;
                val++;
                
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) {
                    d = (d + 1) % 4;
                    nr = r + DR[d];
                    nc = c + DC[d];
                }

                r = nr;
                c = nc;
            }
            
            sb.append('#').append(tc).append('\n');
            for (int[] row : arr) {
                for (int n : row) {
                    sb.append(n).append(' ');
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }
}