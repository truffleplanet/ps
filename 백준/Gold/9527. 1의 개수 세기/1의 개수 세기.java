import java.util.*;
import java.lang.*;
import java.io.*;

/*
 63 * 10^16 < 2^63

자리수에 따라 달라짐.
0, 1
10, 11, 00, 01
110, 111, 100, 101, 010, 011, 000, 001
 
*/

// The main method must be in a class named "Main".
class Main {

    static long[] dp;
    
    public static void main(String[] args) throws Exception {
        dp = new long[64];
        for (int i = 1; i < 64; i++) {
            dp[i] = 2 * dp[i - 1] + (1L << (i - 1));
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(count(B) - count(A - 1));
    }

    static long count(long x) {
        long cnt = 0;
        for (int i = 63; i >= 0; i--) {
            long msbVal = 1L << i;
            if ((x & msbVal) != 0) {
                cnt += dp[i] + x - msbVal + 1;
                x -= msbVal;
            }
        }
        return cnt;
    }
}