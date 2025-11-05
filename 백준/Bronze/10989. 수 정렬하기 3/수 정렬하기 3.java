/*
수의 개수 N(1 ≤ N ≤ 10,000,000) 인데
수의 범위는 10000 이하의 자연수
counting sort의 기회!
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] count = new int[10_001];

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            count[Integer.parseInt(br.readLine())]++;
        }

        for (int i = 1; i < 10001; i++) {
            for (int k = 0; k < count[i]; k++) {
                sb.append(i).append('\n');
            }
        }

        System.out.println(sb);
    }
}