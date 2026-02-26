import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String s = br.readLine();
        String pattern = br.readLine();

        int[] pi = findPi(pattern);
        int count = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    count++;
                    sb.append(i - j + 1).append(' ');
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }

        System.out.println(count);
        System.out.println(sb);
        
    }

    public static int[] findPi(String s) {
        int m =  s.length();
        int[] pi = new int[m];
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                pi[i] = j + 1;
                j++;
            }
        }
        return pi;
    }
}