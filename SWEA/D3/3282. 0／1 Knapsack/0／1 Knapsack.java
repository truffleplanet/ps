import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      StringBuilder sb = new StringBuilder();
      
      int T = Integer.parseInt(br.readLine());
      
      for (int tc = 1; tc <= T; tc++) {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[2][K+1];
        int cur = 0;
        
        for (int i = 0; i < N; i++) {
          int next = cur ^ 1;
          st = new StringTokenizer(br.readLine());
          int v = Integer.parseInt(st.nextToken());
          int c = Integer.parseInt(st.nextToken());
          
          for (int k = 0; k <= K; k++) {
            if (k < v) {
              dp[next][k] = dp[cur][k];
            } else {
              dp[next][k] = Math.max(dp[cur][k-v] + c, dp[cur][k]);
            }
          }
          cur = next;
        }
        
        int ans = Math.max(dp[0][K], dp[1][K]);
        
        sb.append('#').append(tc).append(' ').append(ans).append('\n');
        
      }
      
      System.out.println(sb);
  }
}