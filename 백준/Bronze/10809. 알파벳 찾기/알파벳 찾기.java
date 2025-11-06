import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int MAX = 'z' - 'a';
        int[] idx = new int[MAX + 1];
        Arrays.fill(idx, -1);
        char[] seq = br.readLine().toCharArray();

        for (int i = 0; i < seq.length; i++) {
            int v = seq[i] - 'a';
            if (idx[v] == -1) {
                idx[v] = i;
            }
        }

        for (int i = 0; i <= MAX; i++) {
            sb.append(idx[i]).append(' ');
        }
        
        System.out.println(sb);
    }
}