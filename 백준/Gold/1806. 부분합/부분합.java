import java.util.*;
import java.lang.*;
import java.io.*;
/*
포인터 두개를 사용하고,
부분합이 5보다 작으면 j++
5보다 크면 i++
갑자기 오버플로우 날 일 없으니 int (각 자연수는 최대 10000)

ok.
*/


// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int best = N + 1;
        
        st = new StringTokenizer(br.readLine());
        int[] seq = new int[N + 1];
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
        if (seq[0] == S) {
            System.out.println(1);
            return;
        }
        
        int sum = seq[0];
        for (int i = 0, j = 1; i <= N && j <= N;) {
            if (sum < S) {
                sum += seq[j++];
            } else {
                best = Math.min(best, j - i);
                sum -= seq[i++];
            } 
        }

        
        if (best == N + 1) {
            best = 0;
        }
        System.out.println(best);
        
    }
}