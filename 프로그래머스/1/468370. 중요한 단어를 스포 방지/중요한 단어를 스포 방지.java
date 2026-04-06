/*
1. 스포방지 구간 밖에서 등장한 적이 없는 단어이고
2. 각 스포방지 단어는 유일하다(가려진게 2개여도 하나로 카운트)
*/

import java.util.*;
import java.lang.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int N = message.length();
        
        boolean[] masked = new boolean[N];
        
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                masked[i] = true;
            }
        }
                 
        HashSet<String> revealed = new HashSet<>();
        HashSet<String> secrets = new HashSet<>();
        
        StringBuilder sb = new StringBuilder();
        boolean isMasked = false;
        
        for (int i = 0; i < N; i++) {
            char c = message.charAt(i);
            if (c != ' ') {
                if (masked[i]) {
                    isMasked = true;
                }
                sb.append(c);
            } else {
                String s = sb.toString();
                if (isMasked) {
                    secrets.add(s);
                } else {
                    revealed.add(s);
                }
                sb = new StringBuilder();
                isMasked = false;
            }
        }
        if (sb.length() > 0) {
            String s = sb.toString();
            if (isMasked) {
                secrets.add(s);
            } else {
                revealed.add(s);
            }
        }
    
        int cnt = 0;
        for (String secret: secrets) {
            if (!revealed.contains(secret)) {
                cnt++;
            }
        }
        
        return cnt;
    }
}