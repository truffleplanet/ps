/*
각 order별로
course에 요구된 숫자를 r로 하는 조합을 만든다

comb(orders, r, String);

각 조합을 HashMap<String, Integer> 에 저장하며 카운트

[r] 순서대로.
1. 해쉬맵 val 조회해서 최대값 찾기
2. 해쉬 key:val 조회해서 val이 최댓값인것 전부 정답 리스트에 더하기.

정답 리스트 정렬 후 array변환하고 return

*/

import java.util.*;

class Solution {
    
    HashMap<String, Integer> count;
    
    public String[] solution(String[] orders, int[] course) {
        
        count = new HashMap<>();
        int best[] = new int[11];
        
        for (String order : orders) {
            char[] t = order.toCharArray();
            Arrays.sort(t);
            String sortedOrder = String.valueOf(t);

            for (int r : course) {
                if (order.length() >= r) {
                    comb(sortedOrder, r, 0, new StringBuilder());
                }
            }
        }
        
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            String key = entry.getKey();
            int cnt = entry.getValue();
            best[key.length()] = Math.max(best[key.length()], cnt);
        }
        
        return count.entrySet().stream()
            .filter(entry -> entry.getValue() > 1 && entry.getValue() == best[entry.getKey().length()])
            .map(Map.Entry::getKey)
            .sorted()
            .toArray(String[]::new);

//         for (Map.Entry<String, Integer> entry : count.entrySet()) {
//             String key = entry.getKey();
//             int cnt = entry.getValue();
//             if (cnt == best[key.length()] && cnt > 1) {
//                 out.add(key);
//             }
//         }
        
//         Collections.sort(out);
//         String[] answer = new String[out.size()];
//         for (int i = 0; i < out.size(); i++) {
//             answer[i] = out.get(i);
//         }
//         return answer;
    }
    
    public void comb(String order, int r, int start, StringBuilder sb) {
        if (sb.length() == r) {
            String seq = sb.toString();
            count.put(seq, count.getOrDefault(seq, 0) + 1);
            return;
        }
        
        int n = order.length();
        
        for (int i = start; i < n; i++) {
            sb.append(order.charAt(i));
            comb(order, r, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }        
    }
}