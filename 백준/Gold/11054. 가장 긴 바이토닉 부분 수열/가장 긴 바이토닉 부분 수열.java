/*
바이토닉 수열 S는 
K번째 항까지 엄격하게 증가하고 
K번째 항부터 엄격하게 감소한다.


LIS:
main idea: 매번 subArray를 한칸 씩 더 보면서 확인하기
LIS[i] : i번째 인덱스에서 끝나는 최장 증가 부분 수열의 길이

for (int i = 0; i < N; i++) {
    LIS[i] = 1;
    for (int j = 0; j < i; j++) {
        if (arr[i] < arr[j]) {
            LIS[i] = Math.max(LIS[i], LIS[j] + 1);
        }
    }
}

LIS를 정방향, 역방향으로 각각 하고,

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] LIS = new int[N];
        int[] LDS = new int[N];

        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            LDS[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, LDS[i] + LIS[i]);
        }

        System.out.println(ans - 1);
        
    }
}