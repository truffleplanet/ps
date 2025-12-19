import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int t = 0; t < T; t++) {
            int tc = sc.nextInt();
            int base = sc.nextInt();
            int e = sc.nextInt();
            System.out.println("#"+tc+" "+fastExp(base, e));
        }
    }

    public static int fastExp(int base, int e) {
        int x = base;
        int y = e;
        int z = 1;
        
        while (y > 0) {
            if (y % 2 == 1) {
                z *= x;
            }
            y /= 2;
            x = x * x;
    	}
        return z;
    }
}