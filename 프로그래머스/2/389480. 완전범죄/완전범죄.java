import java.util.*;


class Solution {
    public int solution(int[][] info, int n, int m) {
        int Num = info.length;
        
        int[][] dp = new int[Num][m];

        int a0 = info[0][0];
        int b0 = info[0][1];
        
        for (int j = 0; j < m; j++) {
            if (j < b0) {
                dp[0][j] = a0;
            } else {
                dp[0][j] = 0;
            }
        }
        
        for (int i = 1; i < Num; i++) {
            int aw = info[i][0];
            int bw = info[i][1];
            
            for (int j = 0; j < m; j++) {
                if (j >= bw) {
                    dp[i][j] = Math.min(dp[i - 1][j] + aw, dp[i - 1][j - bw]);
                } else {
                    dp[i][j] = dp[i - 1][j] + aw;
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, dp[Num - 1][j]);
        }
        if (min >= n)
            return - 1;
        else
            return min;
    }
}