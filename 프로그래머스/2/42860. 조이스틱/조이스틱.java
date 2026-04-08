/*
움직임

어떤 지점에서 a 블럭의 크기가 
next --> i 에서 "A" 모인 블럭 건너 뛰고
1. 쭉 가기  --> N - 1
2. 우 -> 좌  --> i + i + (N - next)
3. 좌 -> 우 --> (N - next) + (N - next) + i 
*/

import java.util.*;

class Solution {
    public int solution(String name) {
        char[] nArr = name.toCharArray();
        int N = nArr.length;
        int M = 0;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (nArr[i] != 'A') {
                M++;
                answer += alpha(nArr[i]);
            }
        }
        
        int move = N - 1;
        for (int i = 0; i < N; i++) {
            int next = i + 1;
            while (next < N && nArr[next] == 'A') {
                next++;
            }
            
            move = Math.min(move, 2 * i + (N - next));
            move = Math.min(move, i + 2 * (N - next));
        }
        
        return answer + move;
    }
    
    public int alpha(char c) {
        int m1 = (int) c - 'A';
        int m2 = (int) 'Z' - c + 1;
        return Math.min(m1, m2);
    }
}