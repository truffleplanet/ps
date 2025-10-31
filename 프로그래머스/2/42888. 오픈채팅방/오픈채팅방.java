/*
1. uid별 이름 매핑 하는 hash
2. Enter 0 0, Leave 0 기록 적어두기
3. Enter 시마다 닉네임 업데이트하기 
*/

import java.util.*;
import java.lang.*;

class Solution {
    
    Map<String, String> idToName;
    final String ENTER = "Enter";
    final String LEAVE = "Leave";
    final String CHANGE = "Change";
    
    public String[] solution(String[] record) throws Exception {
        idToName = new HashMap<>();
        List<String> history = new ArrayList<>();
        List<Integer> command = new ArrayList<>();
        
        for (String in : record) {
            StringTokenizer st = new StringTokenizer(in);
            String cmd = st.nextToken();
            
            if (cmd.equals(ENTER)) {
                String uid = st.nextToken();
                String name = st.nextToken();
                idToName.put(uid, name);
                history.add(uid);
                command.add(0);
            } else if (cmd.equals(LEAVE)) {
                String uid = st.nextToken();
                history.add(uid);
                command.add(1);
            } else if (cmd.equals(CHANGE)) {
                String uid = st.nextToken();
                String name = st.nextToken();
                idToName.put(uid, name);
            }
        }
        
          
        String[] answer = new String[history.size()];
        
        for (int i = 0; i < history.size(); i++) {
            int cmd = command.get(i);
            String uid = history.get(i);
            
            if (cmd == 0) {
                answer[i] = enterToString(uid);
            } else {
                answer[i] = leaveToString(uid);
            }
        }
        
        return answer;
    }
    
    public String enterToString(String uid) {
        return String.format("%s님이 들어왔습니다.", idToName.get(uid));
    }
    
    public String leaveToString(String uid) {
        return String.format("%s님이 나갔습니다.", idToName.get(uid));
    }
    
}