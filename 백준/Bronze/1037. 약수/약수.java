import java.util.*;
import java.lang.*;
import java.io.*;

/*
가장 작은 약수와 가장 큰 약수를 곱하면 그 숫자가 된다.
*/

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int min = 1_000_001;
        int max = -1;        
        while(st.hasMoreTokens()) {
            int a = Integer.parseInt(st.nextToken());
            if (a < min) {
                min = a;
            }
            if (a > max) {
                max = a;
            }
        }

        System.out.println(min*max);
    }
}