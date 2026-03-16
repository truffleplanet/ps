import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String pattern = "DKSH";
        
        int[] pi = findPi(pattern);
        int cnt = 0; 
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if (s.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    cnt++;
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        
        System.out.println(cnt);
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