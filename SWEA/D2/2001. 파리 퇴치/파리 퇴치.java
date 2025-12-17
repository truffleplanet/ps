import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] prefix = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    prefix[i][j] = arr[i - 1][j - 1]
                                    + prefix[i - 1][j]
                                    + prefix[i][j - 1]
                                    - prefix[i - 1][j - 1];
                }
            }

            int ans = 0;
            for (int i = M; i <= N; i++) {
                for (int j = M; j <= N; j++) {
                    ans = Math.max(ans, prefix[i][j] 
                                       - prefix[i - M][j] 
                                       - prefix[i][j - M]
                                       + prefix[i - M][j - M]);
                }
            }

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }
        
        System.out.println(sb);
    }
}