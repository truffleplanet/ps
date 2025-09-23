import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        final int MAX = 1000;
        boolean[] isPrime = new boolean[MAX + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for (int i = 2; i * i <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i + i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = 0;
        while(st.hasMoreTokens()) {
            if (isPrime[Integer.parseInt(st.nextToken())])
                cnt++;
        }
        System.out.println(cnt);
    }
}