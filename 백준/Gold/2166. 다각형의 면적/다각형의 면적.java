import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] points = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        long sum1 = (long) points[N-1][0] * points[0][1];
        long sum2 = (long) points[0][0] * points[N-1][1];

        for (int i = 0; i < N - 1; i++) {
            sum1 += (long) points[i][0] * points[i + 1][1];
            sum2 += (long) points[i + 1][0] * points[i][1];
        }

        double ans = Math.abs(sum1 - sum2) / 2.0;

        System.out.printf("%.1f", ans);
        
    }
}