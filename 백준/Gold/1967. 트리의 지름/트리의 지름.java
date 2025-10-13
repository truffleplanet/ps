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

// 2
자식까지 쭉 내려가며
각 루트는 자기가 가진 자손까지의 경로중 가장 긴 경로 반환하기.
top-down dp로 구현하기 
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

    
    static int dfs(int u) {
        int best1 = 0;
        int best2 = 0;
        for (Edge e : tree.get(u)) {
            int h = dfs(e.to) + e.w;
            if (h > best1) {
                best2 = best1;
                best1 = h;
            } else if (h > best2) {
                best2 = h;
            }
        }
        longgest = Math.max(longgest, best1 + best2);
        return best1;
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
        }
        
        longgest = 0;
        dfs(1);
        System.out.println(longgest);
    }
}