/*
N개의 노드, M개의 도로, W개의 웜홀
도로는 양방향, 웜홀은 단방향
웜홀은 시간에 대해 음의 가중치

태스크:
음의 가중치 몇번 처먹고 와서 시작위치에 음수 시간으로 도달할 수 있는지
가능성 여부 판별하기

벨만포드
벨만포드 알고리즘의 동작과정은 다음과 같습니다.
모든 정점까지의 거리를 무한대로 초기화 합니다. 단 출발 정점의 초기값을 0으로 합니다.
정점의 개수 - 1 번 만큼 반복을 진행 합니다.
모든 간선을 순회하며 거리를 갱신합니다.
기존 값보다 더 작은 값으로 업데이트 된다면 거리를 갱신합니다.
음의 사이클을 확인하기 위해 한 번 더 거리를 갱신하여 업데이트 되는지 확인합니다.
업데이트 된다면 음의 사이클이 존재하는 것입니다.
최종적으로 구한 경로들이 출발점에서의 최단 경로 입니다.


*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        final int INF = 100_000_000;

        int T = Integer.parseInt(br.readLine());
        for (int $ = 0; $ < T; $++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge> edgeList = new ArrayList<>();
            int[] dist = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(from, to, w));
                edgeList.add(new Edge(to, from, w));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(from, to, -w));
            }
            
            Arrays.fill(dist, INF);
            dist[edgeList.get(0).from] = 0;

            for (int i = 0; i < N - 1; i++) {
                for (Edge e : edgeList) {
                    int nd = dist[e.from] + e.w;
                    if (nd < dist[e.to]) {
                        dist[e.to] = nd;
                    }
                }
            }

            // cycle check
            boolean updated = false;
            for (Edge e : edgeList) {
                int nd = dist[e.from] + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    updated = true;
                }
            }

            if (updated) {
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }


        } // test case 종료
        System.out.println(sb);
    }
}