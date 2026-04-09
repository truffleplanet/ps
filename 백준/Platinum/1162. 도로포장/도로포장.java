/*
long 사용 필요함. 

*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class HeapNode implements Comparable<HeapNode> {
        int cur;
        int rlxCnt;
        long dist;

        public HeapNode(int cur, int rlxCnt, long dist) {
            this.cur = cur;
            this.rlxCnt = rlxCnt;
            this.dist = dist;
        }

        @Override
        public int compareTo(HeapNode o) {
            // int v1 = Integer.compare(this.rlxCnt, o.rlxCnt);
            // if (v1 != 0) return v1;
            return Long.compare(this.dist, o.dist);
        }
    }

    static class Edge {
        int to;
        int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList.get(u).add(new Edge(v, w));
            adjList.get(v).add(new Edge(u, w));
        }

        PriorityQueue<HeapNode> pq = new PriorityQueue<>();
        long[][] dist = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);            
        }
        dist[1][0] = 0L;
        pq.offer(new HeapNode(1, 0, 0L));

        while (!pq.isEmpty()) {
            HeapNode state = pq.poll();

            if (dist[state.cur][state.rlxCnt] != state.dist) {
                continue;
            }

            if (state.cur == N) {
                continue;
            }

            for (Edge e : adjList.get(state.cur)) {
                if (dist[e.to][state.rlxCnt] > state.dist + e.w) {
                    dist[e.to][state.rlxCnt] = state.dist + e.w;
                    pq.offer(new HeapNode(e.to, state.rlxCnt, dist[e.to][state.rlxCnt]));
                }
                
                if (state.rlxCnt < K && dist[e.to][state.rlxCnt + 1] > state.dist) {
                    dist[e.to][state.rlxCnt + 1] = state.dist;
                    pq.offer(new HeapNode(e.to, state.rlxCnt + 1, state.dist));
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            ans = Math.min(ans, dist[N][i]);
        }
        System.out.println(ans);
    }
}