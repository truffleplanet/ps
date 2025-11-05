/*
시작 -> 도착 도시까지 갈 때 최대 돈을 들고 갈 수 있는 방법은?

어떤 도시에서 버는 돈은 
(도시에서 버는 돈 - 간선 가중치)라고 할 수 있음. 

그런데, 우리는 돈을 최대로 하고 싶고, 최댓값을 구하는 방식의
거리 알고리즘은 모르겠음.

따라서 위의 돈을 모두 음수로 바꾼 후
벨만 포드 알고리즘을 통해
최소 비용을 구한 후, 음수 변환하여 출력하기로 한다. 

다만 벨만포드를 일반적으로 하면,
도착 못하는 곳의 거리도 음수 사이클이 있다면 계속 업데이트 된다.
이를 방지하기 위해 dist[e.from] != INF 조건을 추가한다.

양의 방향으로는 최대 100만이나, 
음의 방향으로는 매 회마다 서로 relaxation되면서 지수적 증가 가능함.
long 사용 필요.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Edge {
        int from;
        int to;
        long w;

        public Edge(int from, int to, long w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    static final int INF = 100_000_000;
    static int N;
    static List<Edge> edgeList;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(from, to, cost)); // 최종형태 +cost - earned
        }

        int[] earns = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            earns[i] = Integer.parseInt(st.nextToken());
        }

        for (Edge e : edgeList) {
            e.w = e.w - earns[e.to];
        }

        long[] dist = new long[N];
        Arrays.fill(dist, INF);
        dist[start] = -earns[start];

        for (int i = 0; i < N - 1; i++) {
            for (Edge e : edgeList) {
                if (dist[e.from] == INF)
                    continue;
                long nd = dist[e.from] + e.w;
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                }
            }
        }

        if (dist[end] == INF) {
            System.out.println("gg");
            return;
        }

        long ans = -dist[end];

        //check cycle
        boolean updated = false;
        for (Edge e : edgeList) {
            if (dist[e.from] == INF)
                continue;
            long nd = dist[e.from] + e.w;
            if (dist[e.to] > nd) {
                dist[e.to] = nd;
                // 업데이트된 사이클에서 bfs로 도달 가능성 확인
                if (bfs(e.to, end)) {
                    updated = true;
                    break;
                }
            }
        }

        if (updated)
            System.out.println("Gee");
        else
            System.out.println(ans);
        
    }

    static boolean bfs(int start, int end) {
        if (start == end)
            return true;
        
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (Edge e : edgeList) {
                if (e.from == cur && !visited[e.to]) {
                    if (e.to == end) {
                        return true;
                    }
                    visited[e.to] = true;
                    q.offer(e.to);
                }
            }
        }

        return false;
    }
        
}
