/*
1x4 - 4x1 2가지
2x2 한가지
ㄴ -> 8가지
번개 -> 4가지
ㅗ -> 4가지 


이다.

가장 쉽게 생각할 수 있는 방법은 다해보는 것이고,

일단 종이의 크기가 최대 500^2이고
시간제한이 2초이므로, 시간에 큰 부담이 없다.

완탐외에 마땅한 방법이 떠오르지 않는다.

따라서 모양별 시작꼭짓점을 잘 정해두어서, 중복 계산을 줄여서
좀 더 스마트한 완탐을 해보기로 한다.

그리고 매우 좋은점이, 모든 칸이 자연수이므로 패딩을 0으로 넣으면
범위검사 if문 줄이고 편하게 구현할 수 있고 정답에 지장도 없다. 

다만 모양이 총 19가지나 되니 구현 실수하기가 매우 좋겠다.

따라서 먼저 계획을 완벽하게 세워보자

어떤 i,j에서 시작하면,
우로 가는 동작부터 시작. 1칸 먼저 더하고,
거기서 ㅁ만들고, 번개 만들고, 3칸가면 ㄴ 4개 ㅗ 2개 만들고,
4칸 만들고.

이제 다시 i,j로 와서 아래로도 같은 짓 반복. 다만 ㅁ는 다시 안해도 됨. 


패딩은 상 1칸, 좌 1칸, 우 2칸, 하 3칸이면 충분하다.
이건 큰 비용이 드는게 아니니 그냥 전부 3칸으로 통일하기로 한다. 
*/


import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N + 6][M + 6];
        int rowStart = 3;
        int rowEnd = N + 3;
        int colStart = 3;
        int colEnd = M + 3;
        // [start, end)
        for (int i = rowStart; i < rowEnd; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = colStart; j < colEnd; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // for (int[] x : grid) {
        //     System.out.println(Arrays.toString(x));
        // }

        int ans = 0;

        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                
                // 1x2 로 표현할 수 있는 것들 먼저
                int cTwo = grid[i][j] + grid[i][j + 1];
                // 정사각형
                int square = cTwo + grid[i + 1][j] + grid[i + 1][j + 1];
                // 번개 둘
                int thunder1 = cTwo + grid[i - 1][j] + grid[i + 1][j + 1];
                int thunder2 = cTwo + grid[i + 1][j] + grid[i - 1][j + 1];

                // 1x3 
                int cThree = cTwo + grid[i][j + 2];
                // ㄴ 4개 
                int right1 = cThree + grid[i - 1][j];
                int right2 = cThree + grid[i + 1][j];
                int right3 = cThree + grid[i - 1][j + 2];
                int right4 = cThree + grid[i + 1][j + 2];
                // ㅗ 2개 
                int castle1 = cThree + grid[i - 1][j + 1];
                int castle2 = cThree + grid[i + 1][j + 1];

                // 1x4
                int flat = cThree + grid[i][j + 3];

                ans = Math.max(ans, square);
                ans = Math.max(ans, thunder1);
                ans = Math.max(ans, thunder2);
                ans = Math.max(ans, right1);
                ans = Math.max(ans, right2);
                ans = Math.max(ans, right3);
                ans = Math.max(ans, right4);
                ans = Math.max(ans, castle1);
                ans = Math.max(ans, castle2);
                ans = Math.max(ans, flat);

                // 사실 위와 [][]의 위치만 바뀌는 
                // 2x1
                cTwo = grid[i][j] + grid[i + 1][j];
                // 번개 둘
                thunder1 = cTwo + grid[i][j - 1] + grid[i + 1][j + 1];
                thunder2 = cTwo + grid[i][j + 1] + grid[i + 1][j - 1];

                // 2x3 
                cThree = cTwo + grid[i + 2][j];
                // ㄴ 4개 
                right1 = cThree + grid[i][j - 1];
                right2 = cThree + grid[i][j + 1];
                right3 = cThree + grid[i + 2][j - 1];
                right4 = cThree + grid[i + 2][j + 1];
                // ㅗ 2개 
                castle1 = cThree + grid[i + 1][j - 1];
                castle2 = cThree + grid[i + 1][j + 1];

                // 1x4
                flat = cThree + grid[i + 3][j];

                ans = Math.max(ans, thunder1);
                ans = Math.max(ans, thunder2);
                ans = Math.max(ans, right1);
                ans = Math.max(ans, right2);
                ans = Math.max(ans, right3);
                ans = Math.max(ans, right4);
                ans = Math.max(ans, castle1);
                ans = Math.max(ans, castle2);
                ans = Math.max(ans, flat);
            }            
        }

        System.out.println(ans);
        
    }
}