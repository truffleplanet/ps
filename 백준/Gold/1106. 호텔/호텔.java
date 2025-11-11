/*
knapsack dp처럼
물건을 순차적으로 하나씩 고르는데,
다만 물건의 수가 제한이 없으므로, 자기 테이블을 참조해서 뒤까지 쭉 업데이트해도 된다.
0 1 2 3 4 5 6 7 8 9 10 11 12
0 0 0 5 0 0 10 0 0 15 0 0 20
0 1 2 5 6 7 10 11 12 15 16 17 20

dp[j] = max(dp[j], dp[j - w(i)] + v(i));

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // c는 최소 인원임.
        // 비용 100 당 인원이 1이고 C는 1000이면
        // 100*1000 = 100,000 
        int maxCost = 100_000;
        int[] dp = new int[maxCost + 1];
        int[] c = new int[N];
        int[] v = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = c[i]; j <= maxCost; j++) {
                dp[j] = Math.max(dp[j], dp[j - c[i]] + v[i]);
                if (dp[j] >= C)
                    break;
            }
        }

        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= C) {
                System.out.println(i);
                return;
            }
        }
        
    }
}