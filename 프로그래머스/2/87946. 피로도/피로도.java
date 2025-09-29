import java.util.*;


/*
하루동안 최대 탐험 던전 갯수.

최소 필요 피로도, 소모 피로도 , 현재 피로도

던전의 개수는 8이하. 
*/

class Solution {
    
    int ans;
    boolean[] selected;
    
    public int solution(int k, int[][] dungeons) {
        int N = dungeons.length;
        selected = new boolean[N];
        ans = 0;
        backtrack(dungeons, k, 0);
        return ans;
    }
    
    public void backtrack(int[][] dungeons, int k, int cnt) {
        int N = dungeons.length;
        
        ans = Math.max(ans, cnt);
        
        if (cnt == N) {
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!selected[i] && k >= dungeons[i][0]) {
                selected[i] = true;
                backtrack(dungeons, k - dungeons[i][1], cnt + 1);
                selected[i] = false;
            }
        }
    }
    
}