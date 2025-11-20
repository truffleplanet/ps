import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] code = br.readLine().toCharArray();

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            char[] target = br.readLine().toCharArray();

            boolean isEqual = true;
            for (int k = 0; k < 5; k++) {
                if (code[k] != target[k]) {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}