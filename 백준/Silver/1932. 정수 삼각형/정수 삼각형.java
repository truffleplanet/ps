/*
맨 왼쪽은 무조건 내려오면서..
맨 오른쪽도 마찬가지로 결정되어있고

나머지는 이제 

i > 0, j > 0
dp[i][j] = trig[i][j] + max(dp[i - 1][j - 1], dp[i - 1][j])

없는 값은요? 그냥 0 패딩.
그러면 오른쪽 따로 구하는 일도 필요 없다.
ㄱㄱ
*/


import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[][] trig = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while(st.hasMoreTokens()) {
                trig[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }
        
        int[][] dp = new int[N][N];
        dp[0][0] = trig[0][0];
        // 좌측 계산
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + trig[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = trig[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }

        int ans = 0;
        for (int j = 0; j < N; j++) {
            ans = Math.max(ans, dp[N - 1][j]);
        }

        System.out.println(ans);
    }
}