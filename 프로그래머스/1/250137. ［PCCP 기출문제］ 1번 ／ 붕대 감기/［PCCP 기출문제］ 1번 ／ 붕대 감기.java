/*
상태를 심플하게 관리하면

현재시간 c, 공격시간 k 이면
(k, c) 구간에서만 회복이 일어남.
즉 range = k - c - 1

현재체력 + (range / 연속성공 요구량) * 보너스 + range * 기본 회복량 
이 공격 전까지의 체력이고
모듈러 연산 통해 최대값 아래로 유지시킨다.

그리고 공격시에 c -> k로 변경하고, 체력 빼고 반복.
*/

import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        final int cap = health;
        final int t = bandage[0];
        final int x = bandage[1];
        final int y = bandage[2];
        int c = 0;
        for (int[] attack : attacks) {
            int k = attack[0];
            int dmg = attack[1];
            
            int interval = k - c - 1;
            health = health + ((interval / t) * y) + (interval * x);
            health = Math.min(health, cap);
            
            health = health - dmg;
            if (health <= 0) {
                return -1;
            }
            c = k;
        }
        return health;
    }
}