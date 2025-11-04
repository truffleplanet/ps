/*
문제 정의:
노드 x부터 수색거리 내에 있는 모든 아이템 수색 가능
어느 지점에 떨어져야 가장 많은 아이템을 회수 가능한가?

풀이:
지역의 개수가 크지 않으므로, 
모든 지역에서 다익스트라 하며, 거리가 수색거리 미만일 때까지 방문하는 모든 노드의 합을 구한다
pq 구현에서는 중복 방문이 불가하므로, pq에서 꺼낸 후 stale 처리한 다음에
노드 값을 출력값에 더해주자.

다익스트라는 결과로서 반환값은, x에서 출발하였을 때 방문할 수 있는 모든 노드들의 값의 합이다.

시간복잡도 O(N * (E + VLogV)) 
110,000
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
        int to;
        int dist;

        public State(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(State o) {
            return this.dist - o.dist;
        }
        
    }
    
    static int V;
    static int RANGE;
    static int[] vals;
    static List<Edge>[] G;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        RANGE = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        vals = new int[V + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++) {
            vals[i] = Integer.parseInt(st.nextToken());
        }

        G = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[from].add(new Edge(to, w));
            G[to].add(new Edge(from, w));
        }

        int ans = -1;

        for (int i = 1; i <= V; i++) {
            ans = Math.max(ans, dijkstra(i)); 
        }

        System.out.println(ans);
    }

    public static int dijkstra(int start) {
        
        int out = 0;
        PriorityQueue<State> pq = new PriorityQueue<>();
        
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[start] = 0;
        pq.offer(new State(start, 0));
        
        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist != dist[cur.to]) {
                continue;
            }

            if (cur.dist > RANGE) {
                break;
            }

            out += vals[cur.to];

            for (Edge e : G[cur.to]) {
                int nd = e.w + cur.dist;
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                    pq.offer(new State(e.to, nd));
                }
            }
        }

        return out;
    }
}