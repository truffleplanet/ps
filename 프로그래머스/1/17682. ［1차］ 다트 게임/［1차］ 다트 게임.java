import java.util.*;

class Solution {
    public int solution(String dartResult) {
        
        int N = dartResult.length();
        
        List<Integer> score = new ArrayList<>();
        score.add(0);
        
        int prev = 0;        
        char[] buffer = new char[2];
        int k = 0;
        for (char ch : dartResult.toCharArray()) {
            
            if (ch >= '0' && ch <= '9' && k < 2) {
                buffer[k++] = ch;
            } else if (k >= 1) {
                int n = 0;
                for (int i = 0; i < k; i++) {
                    n = n * 10 + ((int) buffer[i] - '0');
                }
                score.add(n);
                k = 0;
                prev++;
            }
            
            if (ch == 'S') {
                continue;
            } else if (ch == 'D') {
                score.set(prev, score.get(prev) * score.get(prev));
            } else if (ch == 'T') {
                score.set(prev, score.get(prev) * score.get(prev) * score.get(prev));
            } else if (ch == '*') {
                score.set(prev - 1, score.get(prev - 1) * 2);
                score.set(prev, score.get(prev) * 2);
            } else if (ch == '#') {
                score.set(prev, -score.get(prev));
            }

        }
        
        System.out.println(score);
        
        int ans = 0;
        for (int val : score) {
            ans += val;
        }
        
        return ans;
    }
}