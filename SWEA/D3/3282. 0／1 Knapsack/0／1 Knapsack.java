import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
        	int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                for (int j = K; j >= w; j--) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }

            sb.append('#').append(tc).append(' ').append(dp[K]).append('\n');
        }
        System.out.println(sb);
    }
}