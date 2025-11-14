/*
N의 크기가 작으므로,
이진탐색 방식이 아닌 O(N^2)방식의 dp풀이로 LIS의 길이를 구한다.

LIS를 역추적한다.

LIS (i에서 끝날 때, LIS의 최대값)

dp[i] = max(dp[i], dp[j] + 1) (j < i, arr[i] > arr[j])
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = -1;
        int pos = -1;

        for (int i = 0; i < N; i++) {
            if (dp[i] > ans) {
                ans = dp[i];
                pos = i;
            }
        }

        int[] lis = new int[ans];
        int idx = lis.length - 1;
        lis[idx--] = arr[pos];
        for (int i = pos - 1; i >= 0; i--) {
            if (dp[i] == dp[pos] - 1) {
                lis[idx--] = arr[i];
                pos = i;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(ans).append('\n');
        for (int val : lis) {
            sb.append(val).append(' ');
        }
        System.out.println(sb);
    }
}