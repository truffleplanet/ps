import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.BigInteger;


class Main {
    public static void main(String[] args) throws Exception {
        final int MOD = 1_000_000_000;
        final int DIGIT = 10;
        final int MASK_RANGE = 1 << DIGIT;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N + 1][DIGIT][MASK_RANGE];
        for (int j = 1; j < DIGIT; j++) {
            dp[1][j][1 << j] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int mask = 0; mask < MASK_RANGE; mask++) {
                int nMask;
                nMask = mask | (1 << 1);
                dp[i][1][nMask] += dp[i - 1][0][mask];
                dp[i][1][nMask] %= MOD;
                for (int j = 1; j < DIGIT - 1; j++) {
                    // j + 1
                    nMask = mask | (1 << (j + 1));
                    dp[i][j + 1][nMask] += dp[i - 1][j][mask];
                    dp[i][j + 1][nMask] %= MOD;
                    // j - 1
                    nMask = mask | (1 << (j - 1));
                    dp[i][j - 1][nMask] += dp[i - 1][j][mask];
                    dp[i][j - 1][nMask] %= MOD;
                }
                nMask = mask | (1 << 8);
                dp[i][8][nMask] += dp[i - 1][9][mask];
                dp[i][8][nMask] %= MOD;
            }
        }
        
        int ans = 0;
        for (int j = 0; j < 10; j++) {
            ans += dp[N][j][MASK_RANGE - 1];
            ans %= MOD;
        }
        System.out.println(ans);
    }

}