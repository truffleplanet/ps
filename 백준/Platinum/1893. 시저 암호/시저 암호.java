import java.util.*;
import java.lang.*;
import java.io.*;

/*
    A - 알파벳 순서 (임의의 알파벳 체계, 소문자, 대문자, 숫자)
    W - 원문 
    S - 암호화된 문자열 
*/

class Main {
    public static void main(String[] args) throws Exception {
        final String NO_SOLUTION = "no solution";
        final String UNIQUE = "unique: ";
        final String AMBIGUOUS = "ambiguous: ";
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            char[] A = br.readLine().toCharArray();
            char[] W = br.readLine().toCharArray();
            char[] S = br.readLine().toCharArray();

            HashMap<Character, Character> toNextChar = new HashMap<>();
            for (int i = 0; i < A.length - 1; i++) {
                toNextChar.put(A[i], A[i + 1]);
            }
            toNextChar.put(A[A.length - 1], A[0]);

            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            int[] pi = getPi(W);
            for (int x = 0; x < A.length; x++) {
                if (matchOnce(S, W, pi)) {
                    cnt++;
                    sb.append(x).append(' ');
                }
                for (int idx = 0; idx < W.length; idx++) {
                    W[idx] = toNextChar.get(W[idx]);
                }
            }

            if (cnt > 1) {
                bw.write(AMBIGUOUS);
                bw.write(sb.toString());
                bw.write("\n");
            } else if (cnt == 1) {
                bw.write(UNIQUE);
                bw.write(sb.toString());
                bw.write("\n");
            } else {
                bw.write(NO_SOLUTION);
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static boolean matchOnce(char[] s, char[] w, int[] pi) {
        int j = 0;
        boolean matched = false;
        for (int i = 0; i < s.length; i++) {
            while (j > 0 && s[i] != w[j]) {
                j = pi[j - 1];
            }
            if (s[i] == w[j]) {
                if (j == w.length - 1) {
                    if (matched) {
                        return false;
                    } else {
                        matched = true;
                        j = pi[j];
                    }
                } else {
                    j++;
                }
            }
        }
        if (matched) {
            return true;
        } else {
            return false;
        }
        
    }

    public static int[] getPi(char[] seq) {
        int[] pi = new int[seq.length];
        int j = 0;
        for (int i = 1; i < seq.length; i++) {
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
    
}