/*
5킬로 그램 봉지를 많이 집는게 봉지 수가 적어진다.

최대 5킬로 봉지에서 시작해서 정답 탐색하자.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int s = N / 5;
        int remain = N - (s + 1) * 5;
        for (int i = s; i >= 0; i--) {
            remain += 5;
            if (remain % 3 == 0) {
                System.out.println(i + (remain / 3));
                return;
            }
        }
        System.out.println(-1);
    }
}