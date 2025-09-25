import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[M+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;


        int sqrtM = (int) Math.sqrt(M);
        for (int i = 2; i <= M; i++) {
            if (isPrime[i]) {
                if (i >= N) {
                    sb.append(i).append('\n');
                }

                if (i <= sqrtM) {
                     for (int j = i*i; j <= M; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }

        System.out.println(sb);
        
    }
}