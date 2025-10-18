import java.util.*;
import java.lang.*;

class Solution {
    
    List<Integer>[] G;
    int N;
    int[] count;
    int ans;
    
    public int solution(int n, int[][] wires) {
        N = n;
        
        G = new List[N + 1];
        count = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            G[u].add(v);
            G[v].add(u);
        }
        
        ans = Integer.MAX_VALUE;
        
        dfs(1, 0);
        
        return ans;
    }
    
    int dfs(int cur, int parent) {
        count[cur] = 1;
        
        for (int v : G[cur]) {
            if (v != parent) {
                count[cur] += dfs(v, cur);
            }
        }
        
        if (cur != 1) {
            int diff = Math.abs(count[cur] - (N - count[cur]));
            ans = Math.min(ans, diff);
        }
        
        return count[cur];
    }
    
}