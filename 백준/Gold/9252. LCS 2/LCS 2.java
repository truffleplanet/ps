/*
LCS
if (a[i] == b[j]) then, dp[i][j] = dp[i-1][j-1] + 1;
else then, dp[i][j] = max(dp[i-1][j], dp[i][j-1])
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static char[] a;
    static char[] b;
    static int[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();

        dp = new int[a.length + 1][b.length + 1];

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int best = dp[a.length][b.length];
        char[] seq = new char[best];
        getBestSeq(a.length, b.length, best - 1, seq);
        sb.append(best).append('\n');
        sb.append(String.valueOf(seq));
        System.out.println(sb);
        
        // for (int[] row : dp) {
        //     System.out.println(Arrays.toString(row));
        // }
    }

    public static void getBestSeq(int r, int c, int pointer, char[] seq) {
        if (dp[r][c] == 0 || pointer == -1) {
            return;
        }
        
        int val = dp[r][c];

        if (dp[r - 1][c] == val) {
            getBestSeq(r - 1, c, pointer, seq);
        } else if (dp[r][c - 1] == val) {
            getBestSeq(r, c - 1, pointer, seq);
        } else {
            seq[pointer] = a[r - 1];
            getBestSeq(r - 1, c - 1, pointer - 1, seq);
        }
        
    }
    
}