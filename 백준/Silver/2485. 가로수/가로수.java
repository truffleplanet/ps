/*
문제 정의:
현재 수열이 등차수열이 되도록 수열에 요소를 최소 몇 개 추가 해야 하는지 구하여라.

1 3 7 13 
 -- 초기 상태의 가장 작은 간격에 전부 맞춰야 하나? 
 -- 그런데 다른 간격이 가장 작은 간격으로 나누어지지 않으면..
 -- 최대공약수 문제네
 -- 간격의 최대 공약수를 구하고,
 -- 간격들을 그걸로 나눠서 더하면 됨. 
 --  gcd 비용 * N + 순회 N
제약조건:
가로수의 위치는 정렬된 순서로 주어짐. 
가로수의 수는 최대 10만 
가로수의 좌표는 최대 1_000_000_000 임.(int범위)

처음에 읽으면서 최소 diff 찾기 O(N).


*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    // 유클리드 호제법
    // x, y의 최대 공약
    public static int gcd(int x, int y) {
        if (x % y == 0)
            return y;
        return gcd(y, x % y);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] diff = new int[N - 1];

        // 순회하며 바로 최대공약수 구하고 싶어서 먼저 첫회차만 한번 해줌
        int prev = Integer.parseInt(br.readLine());
        int cur = Integer.parseInt(br.readLine());
        int d = cur - prev;
        diff[0] = d;
        int G = d;
        prev = cur;


        for (int i = 1 ; i < N - 1; i++) {
            cur = Integer.parseInt(br.readLine());
            d = cur - prev;
            diff[i] = d;
            G = gcd(d, G);
            prev = cur;
        }

        int ans = 0;
        for (int i = 0 ; i < N - 1; i++) {
            ans += diff[i] / G - 1; // 나누어서 1이면 최소간격
        }
        
        System.out.println(ans);
    }
}