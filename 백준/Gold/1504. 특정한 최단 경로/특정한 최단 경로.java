/*
두 점을 통과해야한다는 조건을 충족하려면,,?
두 점 중 어디를 먼저 가야하는지는 상관없고 방향성이 없는 그래프니깐 
경유지 x0, x1중 가까운 곳 먼저 가면 된다.
즉, 
1 -> x0 -> x1 -> N
과 같은 경로로 가면 되고

// 풀어보니 아마 x0, x1중 가까운 곳 먼저 가면 된다는 가설이 틀린 것 같다. 
x1->N
x0->N의 거리를 모르니깐.

그러면 현재 방식으로 구현하려면 다익스트라를 6번 해보면 된다.
방문지를 state가 들고 다니는 방식으로 구현한다면, 한 번의 다익스트라로도 가능하긴 할 것임. 

구현:
3번의 다익스트라를 할 것이니, 다익스트라를, 시작점과 목적점 파라미터로 받는 함수로 설계한다.
-- 
또한, 두 정점사이의 간선은 최대 1개라는 조건이 있으니, 복잡한 처리도 필요 없다.

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

    static class State implements Comparable<State> {
        int u;
        int dist;

        public State(int u, int dist) {
            this.u = u;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(State o) {
            return Integer.compare(this.dist, o.dist);
        }
        
    }
    
    static List<List<Edge>> graph; 
    static int V, E;
    

    static int dijkstra(int start, int end) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            
            if (cur.u == end) {
                return cur.dist;
            }

            if (dist[cur.u] != cur.dist) {
                continue;
            }

            for (Edge v : graph.get(cur.u)) {
                int nd = cur.dist + v.w;
                if (dist[v.to] > nd) {
                    dist[v.to] = nd;
                    pq.offer(new State(v.to, nd));
                }
            }
        }

        return -1;
    }

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        graph = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int x0 = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());

        int d00 = dijkstra(1, x0);
        if (d00 == -1) {
            System.out.println(-1);
            return;
        }
        int d10 = dijkstra(1, x1);
        if (d10 == -1) {
            System.out.println(-1);
            return;
        }


        int d01 = dijkstra(x0, x1);
        int d11 = dijkstra(x1, x0);

        int d02 = dijkstra(x1, V);
        if (d02 == -1) {
            System.out.println(-1);
            return;
        }
        int d12 = dijkstra(x0, V);

        int ans = Math.min(d00 + d01 + d02, d10 + d11 + d12);
        System.out.println(ans);
    }
}