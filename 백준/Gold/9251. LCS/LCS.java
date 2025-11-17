/*
공부하고 구현할 때마다 헷갈리는 LCS
이번에는 잘 해낼 수 있을까?


A의 [i], B[j]에서 끝날 때, LCS의 최대 길이

if (A[i] == B[i])
    dp[i][j] = dp[i -1][j - 1] + 1;
else
    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int N = A.length;
        int M = B.length;
        int[][] dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}