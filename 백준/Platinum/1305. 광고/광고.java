import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());
        char[] s = br.readLine().toCharArray();

        int len = L - getPi(s)[L - 1];
        System.out.println(len);
    }

    public static int[] getPi(char[] pattern) {
        int m = pattern.length;
        int[] pi = new int[m];
        int j = 0;
        
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if (pattern[i] == pattern[j]) {
                pi[i] = j + 1;
                j++;
            }
        }
        return pi;
    }
        
}