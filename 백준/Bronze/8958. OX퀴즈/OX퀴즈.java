import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        
        for (int t = 0; t < T; t++) {
            int ans = 0;
            int combo = 1;
            for (char ch : br.readLine().toCharArray()) {
                if (ch == 'O') {
                    ans += combo;
                    combo++;
                } else {
                    combo = 1;
                }
            }
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }
}