import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        int MAX = 15;
        int[][] ans = new int[MAX][MAX];
        int v = 0;
        for (int j = 0; j < MAX; j++) {
            ans[0][j] = j;
        }

        for (int i = 1; i < MAX; i++) {
            for (int j = 1; j < MAX; j++) {
                ans[i][j] = ans[i][j - 1] + ans[i - 1][j];
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            sb.append(ans[Integer.parseInt(br.readLine())][Integer.parseInt(br.readLine())]);
            sb.append('\n');
        }

        System.out.println(sb);
    }
}