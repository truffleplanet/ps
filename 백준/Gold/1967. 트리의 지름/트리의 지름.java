import java.util.*;
import java.lang.*;
import java.io.*;

/*
가장 단순하게 생각해볼 수 있는 방법은
중복 방문을 금지하고, 그 경로 중 최대 경로이니깐
플로이드 워셜로 모든 노드간의 거리 구한 후 
그 중 최대 거리를 찾는 것 

그런데 이게 좀 크니깐.. 좋은 방법은 아닌 것 같고..
시작점을 어디로 잡느냐에 따라 경로를 생각하면
dfs를 모든 지점에서 해봐야되는데
이는 10,000 ^ 2 = 100,000,000 으로 되니
1초 통과 가능.

더 좋은 방법 있는가?
지금은 모르겠음 .일단 2초 통과 가능한 로직이니 ㄱㄱ

*/

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

    static int longgest;
    static List<List<Edge>> tree;
    static boolean[] visited;

    
    static void dfs(int u, int dist) {
        longgest = Math.max(longgest, dist);
        
        for (Edge v : tree.get(u)) {
            if (!visited[v.to]) {
                visited[v.to] = true;
                dfs(v.to, dist + v.w);
                visited[v.to] = false;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tree = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree.get(u).add(new Edge(v, w));
            tree.get(v).add(new Edge(u, w));
        }
        
        visited = new boolean[N + 1];
        longgest = 0;
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, 0);
            visited[i] = false;
        }

        System.out.println(longgest);
    }
}