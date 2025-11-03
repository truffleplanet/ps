import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class Edge {
        int from;
        int to;
        int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        final long INF = 1_000_000_000;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edgeList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(u, v, w));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edgeList) {
                // 문제에서 싸이클을 감지하는 것은 시작 경로와 이어진 곳에 한정됨. 따라서
                // 전역적으로 음의 싸이클 감지하지 않기 위해서 조건 추가.
                if (dist[e.from] == INF)
                    continue;

                long nd = dist[e.from] + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                }
            }
        }

        boolean updated = false;
        for (Edge e : edgeList) {
            // 문제에서 싸이클을 감지하는 것은 시작 경로와 이어진 곳에 한정됨. 따라서
            // 전역적으로 음의 싸이클 감지하지 않기 위해서 조건 추가.
            if (dist[e.from] == INF)
                continue;
            
            long nd = dist[e.from] + e.w;
            if (nd < dist[e.to]) {
                updated = true;
                break;
            }
        }

        if (updated) {
            sb.append(-1).append('\n');
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == INF) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(dist[i]).append('\n');
                }
            }
        }

        System.out.println(sb);
        
    }
}