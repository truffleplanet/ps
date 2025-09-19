import java.util.*;
import java.io.*;

public class Solution {
    // tip: arguments are passed via the field below this editor
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            char[] seq1 = st.nextToken().toCharArray();
            char[] seq2 = st.nextToken().toCharArray();

            int n = seq1.length;
            int m = seq2.length;

            int dp[][] = new int[n + 1][m + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (seq1[i] == seq2[j]) {
                        dp[i + 1][j + 1] = dp[i][j] + 1;
                    } else {
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(dp[n][m]).append('\n');

        }

        System.out.println(sb);
    }
}