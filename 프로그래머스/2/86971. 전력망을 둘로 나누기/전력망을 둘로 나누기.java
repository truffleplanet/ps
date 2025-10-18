import java.util.*;
import java.lang.*;

class Solution {
    
    List<Integer>[] G;
    int N;
    
    
    public int solution(int n, int[][] wires) {
        N = n;
        
        G = new List[N + 1];
        
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            G[u].add(v);
            G[v].add(u);
        }
        
        int ans = Integer.MAX_VALUE;
        
        for (int[] wire : wires) {
            int u = wire[0];
            int v = wire[1];
            G[u].remove(new Integer(v));
            G[v].remove(new Integer(u));
            
            int x1 = bfs(u);
            int x2 = bfs(v);
            
            ans = Math.min(ans, Math.abs(x1 - x2));
            
            G[u].add(v);
            G[v].add(u);
        }
        
        return ans;
    }
    
    int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        q.offer(start);
        
        int cnt = 1;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int v : G[cur]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(v);
                    cnt++;
                }
            }
        }
        
        return cnt;
        
        
    }
    
}