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
            for (int r : course) {
                if (order.length() >= r) {
                    // 문자열을 정렬해서 넘겨줘야함!
                    char[] a = order.toCharArray();
                    Arrays.sort(a);
                    comb(String.valueOf(a), r, 0, "");
                }
            }
        }
        
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            String key = entry.getKey();
            int cnt = entry.getValue();
            best[key.length()] = Math.max(best[key.length()], cnt);
        }
        
        List<String> out = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            String key = entry.getKey();
            int cnt = entry.getValue();
            if (cnt == best[key.length()] && cnt > 1) {
                out.add(key);
            }
        }
        
        Collections.sort(out);
        String[] answer = new String[out.size()];
        for (int i = 0; i < out.size(); i++) {
            answer[i] = out.get(i);
        }
        return answer;
    }
    
    public void comb(String order, int r, int start, String seq) {
        if (seq.length() == r) {
            // System.out.println("order: " + order);
            // System.out.println("seq: " + seq + "\n");
            count.put(seq, count.getOrDefault(seq, 0) + 1);
            return;
        }
        
        int n = order.length();
        
        for (int i = start; i < n; i++) {
            comb(order, r, i + 1, seq + order.charAt(i));
        }        
    }
}