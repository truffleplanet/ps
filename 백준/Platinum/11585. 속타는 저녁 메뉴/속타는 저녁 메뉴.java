import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        char[] pattern = toCharArray(br.readLine(), N);

        int len = N - findPi(pattern)[N - 1];       
        int cnt = 1;
        if (N % len == 0) {
            cnt = N / len;
        }

        int G = gcd(N, cnt);

        StringBuilder sb = new StringBuilder();
        sb.append(cnt / G).append('/').append(N / G);
        System.out.println(sb);
    }

    public static char[] toCharArray(String s, int len) {
        char[] seq = new char[len];
        for (int i = 0, j = 0; i < s.length(); i += 2, j++) {
            seq[j] = s.charAt(i);
        }
        return seq;
    }

    public static int[] findPi(char[] seq) {
        int m = seq.length;
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && seq[i] != seq[j]) {
                j = pi[j - 1];
            }

            if (seq[i] == seq[j]) {
                pi[i] = j + 1;
                j++;
            }
        }
        return pi;
    }

    public static int gcd(int x, int y) {
        if (x % y == 0) {
            return y;
        } else {
            return gcd(y, x % y);
        }
    } 
    
}