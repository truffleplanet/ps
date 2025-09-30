/*
팀 맴버가 추가됨에 따라 각 팀원의 능력치가 계속 변함.
-> 완탐하자.

1. 조합 만들기 
2. 합 계산하기

어느 팀에 속하냐에 따라 점수가 변하지는 않으니깐
조합에 하나 고정시켜놔서 반은 줄이자. 

*/
import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int N;
    static int R;
    static int[][] table;
    static boolean[] selected;
    static int ans;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        R = N / 2;
        table = new int[N][N];
        selected = new boolean[N];
        ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected[0] = true;
        combination(1, 1);
        System.out.println(ans);
    }

    public static void combination(int start, int cnt) {
        if (cnt == R) {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < N; i++) { 
                for (int j = 0; j < N; j++) {
                    if (selected[i] && selected[j]) {
                        sum1 += table[i][j];
                    }

                    if (!selected[i] && !selected[j]) {
                        sum2 += table[i][j];
                    }
                }
            }
            ans = Math.min(ans, Math.abs(sum1 - sum2));
            return;
        }

        for (int i = start; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                combination(i + 1, cnt + 1);
                selected[i] = false;
            }
        }
        
    }
}