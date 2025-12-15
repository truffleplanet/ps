/*
단순한 상태기계
최대 이동 거리는 int범위 이내 
*/


import java.util.*;
import java.lang.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int totalDist = 0;
            int speed = 0;
            while (N-- > 0) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                if (cmd == 0) {
                    totalDist += speed;
                } else if (cmd == 1) {
                    int diff = Integer.parseInt(st.nextToken());
                    speed += diff;
                    totalDist += speed;
                } else if (cmd == 2) {
                    int diff = Integer.parseInt(st.nextToken());
                    speed = Math.max(0, speed - diff);
                    totalDist += speed;
                }
            }

            
            sb.append('#').append(tc).append(' ').append(totalDist).append('\n');
        } 
        
        System.out.println(sb);
    }
}