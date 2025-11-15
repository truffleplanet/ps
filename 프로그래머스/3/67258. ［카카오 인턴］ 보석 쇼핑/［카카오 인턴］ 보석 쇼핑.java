/*
모두 포함되는 가장 짧은 구간..
슬라이딩 윈도우로 한번만 읽기 (구간이 여러개라면 시작 진열대 번호가 가장 작은 구간)
1. HashMap<String, Integer> 로 count.
2. HashMap의 size만큼 보석이 차는 구간
*/

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int N = gems.length;
        HashMap<String, Integer> count = new HashMap<>();
        for (String s: gems) {
            if (!count.containsKey(s)) {
                count.put(s, 0);
            }
        }
        
        int total = count.size();
        int best = Integer.MAX_VALUE;
        int i = 0;
        int cur = 0;
        
        // [i, j]
        for (int j = 0; j < N; j++) {
            count.put(gems[j], count.get(gems[j]) + 1);
            if (count.get(gems[j]) == 1) {
                cur++;
            }
            
            while (cur == total) {
                int range = j - i + 1;
                if (best > range) {
                    best = range;
                    answer[0] = i + 1;
                    answer[1] = j + 1;
                }
                
                count.put(gems[i], count.get(gems[i]) - 1);
                if (count.get(gems[i]) == 0) {
                    cur--;
                }
                i++;
            }
        }
        
        return answer;
    }
}