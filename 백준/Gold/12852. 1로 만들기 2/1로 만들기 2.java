import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static int INF = 10_000_000;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] prev = new int[N + 1];
        int[] dp = new int [N + 1];
        Arrays.fill(dp, INF);
        dp[1] = 0;
        for (int cur = 1; cur < N; cur++) {
            int nextX2 = cur * 2;
            int nextX3 = cur * 3;
            int nextPlus1 = cur + 1;
            
            int nextCost = dp[cur] + 1;
        
            if (nextX2 <= N && dp[nextX2] > nextCost) {
                dp[nextX2] = nextCost;
                prev[nextX2] = cur;
            }

            if (nextX3 <= N && dp[nextX3] > nextCost) {
                dp[nextX3] = nextCost;
                prev[nextX3] = cur;
            }

            if (dp[nextPlus1] > nextCost) {
                dp[nextPlus1] = nextCost;
                prev[nextPlus1] = cur;
            }
        }

        sb.append(dp[N]).append('\n');
        
        int trace = N;
        while (trace != 0) {
            sb.append(trace).append(' ');
            trace = prev[trace];
        }

        System.out.println(sb);
    }
}