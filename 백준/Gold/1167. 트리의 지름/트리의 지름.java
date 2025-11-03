/*
1. 트리는 임의의 정점을 루트로 하여도 항상 트리이다.

2. 그러면 임의의 정점에서 시작해서, 리프까지 일단 가본다.

3. 부모는 리프까지의 거리를 재귀적으로 저장한다.

4. 이렇게 하면, 각 노드들은 (가장 먼 리프에 대한 거리들을 저장하고 있다)

5. 각 노드들을 순회하며 거리 top 2의 합이 가장 큰 것을 찾는다

그 합이 트리의 지름이다.


*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Edge {
        int to;
        int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    
    static int ans;
    static int N;
    static List<Edge>[] G;
    static int[] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        G = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) 
                    break;
                int w = Integer.parseInt(st.nextToken());
                G[from].add(new Edge(to, w));
            }
        }

        ans = 0;
        dp = new int[N + 1];
        dfs(-1, 1);
        
        System.out.println(ans);
    }

    static int dfs(int parent, int u) {
        int max1 = 0;
        int max2 = 0;

        for (Edge e : G[u]) {            
            int v = e.to;
            int w = e.w;
            if (v == parent)
                continue;            

            int childDist = w;
            if (dp[v] != 0) {
                childDist += dp[v];
            } else {
                childDist += dfs(u, v);
            }
            
            if (childDist > max1) {
                max2 = max1;
                max1 = childDist;
            } else if (childDist > max2) {
                max2 = childDist;
            }
        }


        ans = Math.max(ans, max1 + max2);

        dp[u] = max1;
        return dp[u];        
        
    }
    
}