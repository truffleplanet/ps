import java.util.*;

/*
IE
EI 동의~비동의
이런식으로 관리해서 R T C F J M A N 각각에 값 가능.

그 후 R - T, C - F, J - M, A - N 각각 비교 .
사전순이니 위 순서대로 비교하면 되긴 함.

좀더 심플한 방법은? 애초에 값을 하나로 관리하기.. 이면 되겠지만, 일단 가자
*/

class Solution {
    public String solution(String[] survey, int[] choices) {
        int N = survey.length;
        
        Map<Character, Integer> score = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            int sc = choices[i];
            if (sc < 4) {
                char ch = survey[i].charAt(0);
                int cur = score.getOrDefault(ch, 0);
                score.put(ch, 4 - sc + cur);
            } else if (sc == 4) {
                continue;
            } else {
                char ch = survey[i].charAt(1);
                int cur = score.getOrDefault(ch, 0);
                score.put(ch, sc - 4 + cur);
            }
        }
        
        String answer = "";

        if (score.getOrDefault('R', 0) < score.getOrDefault('T', 0))
            answer += "T";
        else
            answer += "R";
        
        if (score.getOrDefault('C', 0) < score.getOrDefault('F', 0))
            answer += "F";
        else
            answer += "C";

        if (score.getOrDefault('J', 0) < score.getOrDefault('M', 0))
            answer += "M";
        else
            answer += "J";

        if (score.getOrDefault('A', 0) < score.getOrDefault('N', 0))
            answer += "N";
        else
            answer += "A";

        return answer;
    }
}