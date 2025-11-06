/*
1.
모든 달 28일
년 28 * 12일
무조건 일로 관리하고, 출력시에 년월일로 변경하기


*/
import java.util.*;
import java.lang.*;

class Solution {
    
    // parsing -> day
    final int M = 28;
    final int Y = M * 12;
    
    public int parse(String date) throws Exception {
        StringTokenizer st = new StringTokenizer(date, ".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        
        return (year * Y) + ((month - 1) * M) + day;
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) throws Exception {
        
        HashMap<String, Integer> termMap = new HashMap<>();
        
        int now = parse(today);
        
        // term map
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            String key = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            termMap.put(key, val * M);
        }
        
        
        
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String pvc = privacies[i];
            StringTokenizer st = new StringTokenizer(pvc);
            int d = parse(st.nextToken());
            int term = termMap.get(st.nextToken());
            int nd = d + term - 1;// 당일 포함
            if (nd < now) {
                ans.add(i + 1);
            }
        }
        
        // return
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}