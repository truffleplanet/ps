import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

            char[] seq1 = br.readLine().toCharArray();
            char[] seq2 = br.readLine().toCharArray();

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

        System.out.println(dp[n][m]);
    }
}