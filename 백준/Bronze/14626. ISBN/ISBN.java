import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] mul = {1, 3};
        String isbn = br.readLine();
        int where = 0;
        int p = 1;
        int M = isbn.charAt(12) - '0';
        int tot = 0;
        for (int i = 0; i < 12; i++) {
            char x = isbn.charAt(i);
            if (x == '*') {
                p = mul[where];
                where ^= 1;
                continue;
            }
            tot += mul[where] * (x - '0');            
            where ^= 1;
        }

        // (p * x) % 10 = 10 - (tot % 10)
        for (int i = 0; i <= 9; i++) {
            int x = p * i;
            int nt = tot + x;
            if ((10 - nt % 10) % 10 == M) {
                System.out.println(i);
                return;
            }
        }
    }
}