import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        final int INF = 20_000_000;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // init
        int N = Integer.parseInt(br.readLine());
        int[][] W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int MASK = (1 << N) - 1;
        // solution 
        int[][] dp = new int[MASK + 1][N];
        for (int i = 0; i <= MASK; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[1 << 0][0] = 0;
        for (int mask = 1; mask <= MASK; mask++) {
            for (int start = 0; start < N; start++) {
                if ((mask & (1 << start)) == 0) {
                    continue;
                }
                if (dp[mask][start] == INF) {
                    continue;
                }
                for (int end = 0; end < N; end++) {
                    if (W[start][end] == 0) {
                        continue;
                    }
                    if ((mask & (1 << end)) != 0) {
                        continue;
                    }
                    int nMask = mask | (1 << end);
                    dp[nMask][end] = Math.min(dp[nMask][end], 
                                             dp[mask][start] + W[start][end]);
                }
            }
        }

        int ans = INF;
        for (int i = 1; i < N; i++) {
            if (dp[MASK][i] == INF || W[i][0] == 0) {
                continue;
            }
            ans = Math.min(ans, dp[MASK][i] + W[i][0]);
        }
        System.out.println(ans);
    }    
}