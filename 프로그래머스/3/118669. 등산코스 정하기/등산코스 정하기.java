/*
조건:
출입구, 쉼터, 산봉우리가 있음.
출입구에서 산봉우리까지 최소 intensity(어차피 왕복이고, 똑같음)

출력:
산봉우리 번호와 intensity 최솟값.
최소 intensity 등산 코스가 여러개라면 그 중 산봉우리 번호가 가장 낮은 등산코스


풀이:
갈 수 있는 곳은 산봉우리 아니면 쉼터뿐인데,
intensity는 경로내의 최댓값 하나만 따지면 된다.


1. 출입구부터 멀티소스 다익스트라.
2. 다만 가중치 업데이트가 이동시 intensity와 기존 intensity의 최댓값으로 업데이트 됨.
3. 가장 봉우리 번호가 낮은 등산코스를 알아야하므로,
    산봉우리에 도착하면 최소 intensity업데이트하고
    그것과 같은 intensity가진 모든 노드들은 poll해봐야한다.

*/

import java.util.*;

class Solution {
    
    public class Edge {
        int to;
        int intensity;
        
        public Edge(int to, int intensity) {
            this.to = to;
            this.intensity = intensity;
        }
    }
    
    public class State implements Comparable<State>{
        int from;
        int intensity;
        
        public State(int from, int intensity) {
            this.from = from;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(State o) {
            return Integer.compare(this.intensity, o.intensity);
        }
    }
    
    final static int GATE = 1;
    final static int SUMMIT = 2;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] nodeType = new int[n + 1];
        for (int x : gates) {
            nodeType[x] = GATE;
        }
        for (int x : summits) {
            nodeType[x] = SUMMIT;
        }
        
        List<Edge>[] G = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            G[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int w = path[2];
            G[from].add(new Edge(to, w));
            G[to].add(new Edge(from, w));
        } // 이동 조건 검사는 모두 다익스트라에서 하기로 함. 
        
        int[] answer = new int[2];
        answer[1] = -1;
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<State> pq = new PriorityQueue<>();
        for (int x : gates) {
            dist[x] = 0;
            pq.offer(new State(x, 0));
        }
        
        while (!pq.isEmpty()) {
            State cur = pq.poll();
            
            if (cur.intensity > dist[cur.from]) {
                continue;
            }
            
            if (nodeType[cur.from] == SUMMIT) {
                if (answer[1] == -1) {
                    answer[1] = cur.intensity;
                    answer[0] = cur.from;
                } else if (cur.intensity > answer[1]) {
                    break;
                } else if (answer[0] > cur.from) {
                    answer[0] = cur.from;
                }
                continue;
            }
            
            for (Edge e : G[cur.from]) {
                if (nodeType[e.to] == GATE)
                    continue;
                int nd = Math.max(cur.intensity, e.intensity);
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                    pq.offer(new State(e.to, nd));
                }
            }
        }
        
        return answer;
    }
}