import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String line;
        while((line = br.readLine()).charAt(0) != '.') {
            int n = line.length();
            int[] pi = getPi(line);
            int len = n - pi[n - 1];

            if (n % len != 0) {
                sb.append('1');
            } else {
                sb.append(n / len);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static int[] getPi(String seq) {
        int n = seq.length();
        int[] pi = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && seq.charAt(i) != seq.charAt(j)) {
                j = pi[j - 1];
            }
            if (seq.charAt(i) == seq.charAt(j)) {
                pi[i] = j + 1;
                j++;
            }
        }
        return pi;
    }
    
}