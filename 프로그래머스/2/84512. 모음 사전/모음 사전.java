import java.util.*;

class Solution {
    
    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    char[] build;
    String ans;
    int answer;
    int cnt;
    public int solution(String word) {
        ans = word;
        build = new char[5];
        
        cnt = 0;
        answer = 0;
        dfs(0);
        return answer;
    }
    
    private void dfs(int depth) {
        
        if (depth > 0) {
            if (String.valueOf(Arrays.copyOf(build, depth)).equals(ans)) {
                answer = cnt;
                return;
            }
        }
        
        if (depth == 5) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            build[depth] = vowels[i];
            cnt++;
            
            if (answer != 0)
                return;
            
            dfs(depth + 1);
        }
        
    }
}