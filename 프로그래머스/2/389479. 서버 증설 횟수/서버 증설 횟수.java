/*
유효 시간이 Queue에 들어가고,


*/

import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        Queue<Integer> q = new ArrayDeque<>();
        int cap = m; // m 미만 수용 가능이 시작 세팅.
        int cnt = 0;
        for (int i = 0; i < 24; i++) {
            
            while (!q.isEmpty() && q.peek() <= i) {
                q.poll();
                cap -= m;
            } // 시간이 지난 서버 폐기
            
            if (players[i] >= cap) {
                
                while (players[i] >= cap) {
                    q.offer(i + k);
                    cap += m;
                    cnt++;
                }
            }
            
        }
        
        return cnt;
    }
}