import java.util.*;
import java.lang.*;
import java.io.*;
/*
10,000,000,000,000
9,223,372,036,854,775,807

*/
// The main method must be in a class named "Main".
class Main {

    final static long MOD = 1_000_000_007;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        long ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long denom = Long.parseLong(st.nextToken());
            long num = Long.parseLong(st.nextToken());

            long g = gcd(num, denom);
            denom /= g;
            num /= g;

            long denom_inv = fastExp(denom, MOD - 2);

            ans += (denom_inv * num) % MOD;
        }
        System.out.println(ans % MOD);
    }

    public static long gcd(long x, long y) {
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }

    public static long fastExp(long base, long e) {
        long res = 1;
        long x = base;
        long y = e;

        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            y /= 2;
        }

        return res;
    }
}