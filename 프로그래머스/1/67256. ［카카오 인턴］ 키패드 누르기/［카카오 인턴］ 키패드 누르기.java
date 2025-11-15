/*
state machine

구현 아이디어
1. 1 ~ 9를 0 ~ 8 로, *, 0, #을  9~11로 표현한다.
2. 각 숫자간 이동 거리를 구하는 함수를 만든다.
 
3. 매턴 main hand, second hand 위치와 새로운 숫자의 거리를 구하고, 규칙대로 이동시킨다.
*/

import java.lang.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        int l = 9;
        int r = 11;
        boolean leftHanded = false;
        if (hand.equals("left")) {
            leftHanded = true;
        } 
        
        for (int next : numbers) {
            int pos = -1;
            if (next == 0) {
                pos = 10;
            } else {
                pos = next - 1;
            }
            
            if (pos % 3 == 0) { 
                l = pos;
                sb.append('L');
            } else if (pos % 3 == 2) { 
                r = pos;
                sb.append('R');
            } else { 
                int lDist = getDistance(l, pos);
                int rDist = getDistance(r, pos);
                if (lDist < rDist) {
                    l = pos;
                    sb.append("L");
                } else if (rDist < lDist) {
                    r = pos;
                    sb.append("R");
                } else {
                    if (leftHanded) {
                        l = pos;
                        sb.append("L");
                    } else {
                        r = pos;
                        sb.append("R");
                    }
                }
            }
        }
        
        return sb.toString();
    }
    
    public int getDistance(int cur, int next) {
        if (cur == next) {
            return 0;
        }
        int rowMove = Math.abs((cur / 3) - (next / 3));
        int colMove = Math.abs((cur % 3) - (next % 3));
        return rowMove + colMove;
    }
}