import java.util.*;
import java.lang.*;
import java.io.*;

/*
    풀이 1
    - O(H * W)
    - 2차원 배열 아래쪽 행부터 우측으로 스캔하면서, 좌우 막힌 것 숫자 세기

    잘 하면 그냥 W정보로만도 가능할 것 같은데 잘 모르겠음. 
*/

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int heights[] = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int h = 0; h < H; h++) {
            int startW = 0;
            int cnt = 0;
            while (startW < W && heights[startW] <= h) {
                startW++;
            }
            for (int w = startW + 1; w < W; w++) {
                if (heights[w] <= h) {
                    cnt++;
                } else {
                    ans += cnt;
                    cnt = 0;
                }
            }
        }

        System.out.println(ans);
        
    }
}