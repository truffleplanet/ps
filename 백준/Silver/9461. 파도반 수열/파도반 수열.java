import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        long[] dp = new long[101];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        
        for (int i = 3; i < 101; i++) {
            dp[i] = dp[i-3] + dp[i-2];
        }
        
        int T = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < T; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        }
        
        System.out.println(sb);
    }
}