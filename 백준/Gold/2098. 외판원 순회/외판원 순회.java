import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static final int INF = 20_000_000;
    static int N;
    static int[][] W;
    static int[][] dp;

    static int FULL_MASK;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        FULL_MASK = (1 << N) - 1;

        int ans = dfs(0, 1);
        System.out.println(ans);
        

    }

    static int dfs(int cur, int mask) {
        if (mask == FULL_MASK) {
            if (W[cur][0] != 0) {
                return W[cur][0];
            }
            return INF;
        }

        if (dp[cur][mask] != -1) {
            return dp[cur][mask];
        }

        dp[cur][mask] = INF;
        for (int i = 0; i < N; i++) {
            if (W[cur][i] == 0 || (mask & (1 << i)) != 0) {
                continue;
            }

            int nCost = W[cur][i] + dfs(i, mask | (1 << i));
            dp[cur][mask] = Math.min(dp[cur][mask], nCost);
        }
        return dp[cur][mask];
    }
}