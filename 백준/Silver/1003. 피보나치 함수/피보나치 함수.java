
/*
fibo(0) 호출 횟수를 count하는 배열 zero
fibo(1) 호출 횟수를 count하는 배열 one

zero[0] = 1
zero[1] = 0
i > 1
zero[i] = zero[i - 1] + zero[i - 2]

one[0] = 0
one[1] = 1
i > 1
one[i] = zero[i - 1] + zero[i - 2]  
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        int[] zero = new int[41];
        int[] one = new int[41];

        zero[0] = 1; zero[1] = 0;
        one[0] = 0; one[1] = 1;

        for (int i = 2; i < 41; i++) {
            zero[i] = zero[i - 2] + zero[i - 1];
            one[i] = one[i - 2] + one[i - 1];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int x = Integer.parseInt(br.readLine());
            sb.append(zero[x]).append(' ').append(one[x]).append('\n');
        }
        
        System.out.println(sb);
    }
}