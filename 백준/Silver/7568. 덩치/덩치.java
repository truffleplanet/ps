/*
두 값이 모두 커야만 큰 덩치임
x, y 로 정렬해두고

다음 랭크라고 할 수 있는게 나오면 등수 부여
다만 입력 순서를 유지하며 출력해야 하기에 인덱스를 가지고 있어야 한다. 

==> 정렬하고 선택하면 안된다.
==> 키, 몸무게를 동시에 고려할 수 없음


*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            table[i][0] = Integer.parseInt(st.nextToken());
            table[i][1] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int k = 1;
            
            for (int j = 0; j < N; j++) {
                if (table[j][0] > table[i][0] && table[j][1] > table[i][1]) {
                    k++;
                }
            }

            sb.append(k).append(' ');
        }
        System.out.println(sb);
    }
}