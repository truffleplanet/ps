/*
등산 경로가 있고
휴식없이 이동하는 가장 긴 시간 -> intensity
휴식 -> 쉼터, 산봉우리 방문
산봉우리 - 한번만 방문
출입구 - 처음과 끝 한번만 방문(원래의 출입구로 돌아와야함)
intensity가 최소인 등산 코스 찾기.

풀이:
1. 가는 길과 오는 길의 intensity는 다르지 않으므로, 출입구에서 산봉우리까지 최소 intensity 찾기임.
2. 모든 출입구에서, 산봉우리들에 대한 최소 intensity 찾기.
3. 현재 intensity보다 작아질 수 있음? (없음)
4. 따라서 intensity를 우선순위 큐의 key로 두고 다익스트라하다가 산봉우리 만나면 끝내면 됨.
5. 라고 생각했는데, 해당 intensity 노드까지는 전부 확인해야함. 왜냐하면, intensity가 엄격하게 증가하지 않음.
*/

import java.util.*;

class Solution {
    
    static class Edge {
        int to;
        int w;
        
        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
         
    }
    
    static class HeapNode implements Comparable<HeapNode> {
        int from;
        int intensity;
        
        public HeapNode(int from, int intensity) {
            this.from = from;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(HeapNode o) {
            int v1 = Integer.compare(this.intensity, o.intensity);
            return v1;
            
        }
        
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        final int REST = 0;
        final int GATE = 1;
        final int SUMMIT = 2;
        
        List<List<Edge>> G = new ArrayList<>();
        int[] type = new int[n + 1];
        int[] dist = new int[n + 1];
        PriorityQueue<HeapNode> pq = new PriorityQueue<>();

        for (int i = 0; i <= n; i++) {
            G.add(new ArrayList<>());
        } // 그래프 초기화 
        
        for (int[] path : paths) {
            int u = path[0];
            int v = path[1];
            int w = path[2];
            G.get(u).add(new Edge(v, w));
            G.get(v).add(new Edge(u, w));
        } // 인접 리스트 생성 
                
        Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열 초기화 

        for (int v : summits) { 
            type[v] = SUMMIT;
        } // 타입 배열 초기화 
        
        for (int v : gates) {
            type[v] = GATE;
            dist[v] = 0;
            pq.offer(new HeapNode(v, 0));
        } // 타입 배열 초기화 및 멀티소스 다익스트라 시작
        
        
        int[] ans = {-1, Integer.MAX_VALUE}; // sentinel value
        while (!pq.isEmpty()) {
            HeapNode node = pq.poll();
            int u = node.from;
            int d = node.intensity;
            
            if (dist[u] < d) { // stale
                continue;
            }
            
            if (type[u] == SUMMIT) { // 봉우리면, 해당 노드의 마지막점.
                if (ans[0] == -1) { // 첫 정상일 때.
                    ans[0] = u;
                    ans[1] = d;
                    continue; 
                } else {
                    if (d > ans[1]) { // 만약, intensity 최솟값보다 큰 노드면 종료
                        break;
                    } 
                    
                    if (ans[0] > u) { // 봉우리 넘버가 작은 것을 정답으로하기 
                        ans[0] = u;
                    }
                }
                continue;
            } // 봉우리면 그 노드에서 더 탐색하면 안되니 continue
            
            for (Edge e : G.get(u)) {
                int nd = (d > e.w) ? d : e.w;
                int v = e.to;
                
                if (type[v] == GATE)
                    continue;
                
                if (dist[v] > nd && nd <= ans[1]) {
                    dist[v] = nd;
                    pq.offer(new HeapNode(v, nd));
                }
            }
        }
        
        return ans;
        
    }
}