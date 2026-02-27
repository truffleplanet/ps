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
        final String UNIQUE = "unique";
        final String AMBIGUOUS = "ambiguous";
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
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

            List<Integer> ans = new ArrayList<>();
            int[] pi = getPi(W);
            for (int x = 0; x < A.length; x++) {
                if (matchOnce(S, W, pi)) {
                    ans.add(x);
                }
                for (int idx = 0; idx < W.length; idx++) {
                    W[idx] = toNextChar.get(W[idx]);
                }
            }

            if (ans.size() > 1) {
                sb.append(AMBIGUOUS).append(':').append(' ');
                for (int x : ans) {
                    sb.append(x).append(' ');
                }
            } else if (ans.size() == 1) {
                sb.append(UNIQUE).append(':').append(' ');
                sb.append(ans.get(0));
            } else {
                sb.append(NO_SOLUTION);
            }
            sb.append('\n');
        }
        System.out.println(sb);
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