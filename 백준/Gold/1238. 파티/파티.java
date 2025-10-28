/*
정방향 그래프, 역방향 그래프 하나씩 가지고 있고

X에서 정방향 그래프로 각 마을에 가는 건 돌아가는 시간
X에서 역방향 그래프로 각 마을에 가는 건 오는 시간

다익스트라로 해보자
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
        int pos;
        int dist;

        public State(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(State o) {
            return this.dist - o.dist;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Edge>[] G = new List[N + 1];
        List<Edge>[] R_G = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
            R_G[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[from].add(new Edge(to, w));
            R_G[to].add(new Edge(from, w));
        }

        int[] dist = dijkstra(G, N, X);
        int[] r_dist = dijkstra(R_G, N, X);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist[i] + r_dist[i]);
        }

        System.out.println(ans);
    }

    static int[] dijkstra(List<Edge>[] G, int N, int X) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[X] = 0;
        pq.offer(new State(X, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (dist[cur.pos] != cur.dist) {
                continue;
            }

            for (Edge e : G[cur.pos]) {
                int nd = e.w + cur.dist;
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                    pq.offer(new State(e.to, nd));
                }
            }
        }
        return dist;
    }
}