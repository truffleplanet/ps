/*
만약에 brute-force하게 구현하면, 
모든 M의 시작점에서 검사하므로, O(N*M)이 된다
이러면 통과할수가 없다.

중복검사를 줄여야한다.
다른 방법 뭐가 있을까..?
일단 P_N이 I, O만 반복되는 관계라는 점을 생각해보자.

길이가 3을 시작으로 +2씩 늘어나고, 
i > j, Pi, Pj일 때
Pj를 Pi의 0번 idx부터 2칸씩 슬라이드할 수 있는 횟수가 겹치는 횟수이다.

그러면 Pi에서 Pj가 나타날 수 있는 횟수는 i-j + 1이다.


S을 읽으며 포함된 Px(x >= N)들을 전부 구한다.(O(M))
구하는 동시에, 2^(x - N)을 정답 값에 더한다.

I -> O 
O -> I 의 전이만 허용

최초에 degree= -1 에서 시작

I -> I 일 시, degree = 0;
I -> O 일 시, 진행
O -> I 일 시 degree++
O -> O 일 시 degree = -1;

I -> O
O -> I 아닌 전이 일 시는
현재 degree로 값 계산 후 ans에 합산, degree 초기화.

S를 전부 읽은 후 degree가 0이 아니면 남은 값 계산 후 종료.

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
public class Main {

    
    static int cal(int N, int degree) {
        if (N > degree)
            return 0;
        
        return degree - N + 1;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] line = br.readLine().toCharArray();

        int ans = 0;
        int degree = -1;
        char prev = 'O';
        for (int i = 0; i < M; i++) {
            char cur = line[i];
            if (prev == 'I') {
                if (cur == 'I') {
                    ans += cal(N, degree);
                    degree = 0;
                } else {
                    ;
                }
            } else {
                if (cur == 'I') {
                    degree++;
                } else {
                    ans += cal(N, degree);
                    degree = -1;
                }
            }
            prev = cur;
        }

        ans += cal(N, degree);
    
        System.out.println(ans);
    }
}