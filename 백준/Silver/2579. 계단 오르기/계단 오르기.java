/*
이것도 결국 상태기계이다

어느 시점에서 상태는 다음과 같은 정보를 필요로 한다
    - 현재 계단 위치
    - 연속해서 오른 계단의 수
    - 점수의 합

전이의 규칙은 
- 연속해서 오른 계단의 수와
- 마지막 계단을 밟아야한다는 조건
에 따라 1칸을 올라가거나 2칸을 올라가는 것을 선택할 수 있다. 

그런데 우리는 최대 점수를 알고 싶고,
이 경우에는 부분문제로 다음문제의 해를 구할 수 있다.

만약에 현재 연속해서 오른 계단의 수가 2이면
이전에 연속해서 오른 계단의 수가 1이고, 위치는 직전 계단인 상태에서 전이로 만들어졌을 것이고

만약에 현재 연속해서 오른 계단의 수가 1이면
- 이전에 연속해서 오른 계단의 수가 2이고 위치는 2칸 전에서 전이
- 이전에 연속해서 오른 계단의 수가 1이고 위치는 2칸 전에서 전이
로 만들어질 수 밖에 없다.

이로써 우리는 dp의 일반항을 알게 되었다.

dp[현재 위치][연속해서 오른 계단 수]

dp[0][1] = score[0]
dp[0][2] = NAN (편의상 0으로 두어도 정답에 지장 없음)
dp[1][1] = score[1]
dp[1][2] = score[0] + score[1]

cur > 1
dp[cur][1] = max(dp[cur - 2][1], dp[cur - 2][0]) + score[cur]
dp[cur][2] = dp[cur - 1][1] + score[cur]
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(score[0]);
            return;
        }

        int[][] dp = new int[N][3];
        
        dp[0][1] = score[0]; dp[0][2] = 0;
        dp[1][1] = score[1]; dp[1][2] = score[0] + score[1];

        for (int cur = 2; cur < N; cur++) {
            dp[cur][1] = Math.max(dp[cur - 2][1], dp[cur - 2][2]) + score[cur];
            dp[cur][2] = dp[cur - 1][1] + score[cur];
        }

        System.out.println(Math.max(dp[N - 1][1], dp[N - 1][2]));
        
    }
}