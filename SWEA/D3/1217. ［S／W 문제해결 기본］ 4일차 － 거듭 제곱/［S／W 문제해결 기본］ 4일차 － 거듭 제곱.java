import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int t = 0; t < T; t++) {
            int tc = sc.nextInt();
            int base = sc.nextInt();
            int e = sc.nextInt();
            System.out.println("#"+tc+" "+pow(base, e));
        }
    }

    public static int pow(int base, int e) {
        if (e == 0) {
            return 1;
        }

        int half = pow(base, e / 2);

        if (e % 2 == 1) {
            return half * half * base;
        } else {
            return half * half;
        }

    }
}
