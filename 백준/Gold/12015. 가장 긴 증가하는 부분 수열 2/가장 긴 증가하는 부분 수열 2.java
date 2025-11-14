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
        
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        for (int i = 0 ; i < N; i++) {
            int l = upperBound(dp, arr[i]);
            if (dp[l - 1] < arr[i] && arr[i] < dp[l]) {
                dp[l] = arr[i];
            }
        }

        int ans = 0;
        for (int l = 0; l <= N; l++) {
            if (dp[l] < Integer.MAX_VALUE) {
                ans = l;
            } else {
                break;
            }
        }

        System.out.println(ans);
    }

    public static int upperBound(int[] lis, int key) {
        int l  = 0;
        int r = lis.length;

        while (l < r) {
            int mid = (l + r) >> 1;

            if (lis[mid] <= key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
    
}