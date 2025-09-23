/*
결국 그래프문제임
상태 관리를 하면 되는데,
알고 최대 요구량, 
코딩력 최대 요구량은

정해져있으니 먼저 그것을 찾고,

이제 시간순으로 다익스트라하면,
매번 pq에 1시간 공부하는거 넣고,
또, 풀 수 있는 문제도 다 풀어보고

그러다가 요구량 넘는 노드 poll되면 종료하기.
맞나? 테케검증해보자

stale 처리는 할 필요가 없어 보인다. 어차피 time 낮은 순으로 보며,
최단 time 답이 나올 것이기 때문임.
그래도 151*151 공간 할애해서 stale처리 하자(효율위해)
*/

import java.util.*;
class Solution {
    static class HeapNode implements Comparable<HeapNode> {
        int time;
        int alp;
        int cop;
        
        public HeapNode(int time, int alp, int cop) {
            this.time = time;
            this.alp = alp;
            this.cop = cop;
        }
        
        @Override
        public int compareTo(HeapNode o) {
            return Integer.compare(this.time, o.time);
        }
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        int N = problems.length;
        int ALP_REQ = -1;
        int COP_REQ = -1;
        for (int i = 0; i < N; i++) {
            int alp_req = problems[i][0];
            int cop_req = problems[i][1];
            
            if (ALP_REQ < alp_req)
                ALP_REQ = alp_req;
            
            if (COP_REQ < cop_req)
                COP_REQ = cop_req;
        }
        
        PriorityQueue<HeapNode> pq = new PriorityQueue<>();
        pq.offer(new HeapNode(0, alp, cop));
        
        boolean[][] visited = new boolean[151][151];
        
        while (!pq.isEmpty()) {
            HeapNode cur = pq.poll();
            
            if (cur.alp > 150)
                cur.alp = 150;
            if (cur.cop > 150)
                cur.cop = 150;
            
            if (visited[cur.alp][cur.cop])
                continue;
            
            visited[cur.alp][cur.cop] = true;
            
            if (cur.alp >= ALP_REQ && cur.cop >= COP_REQ)
                return cur.time;
            
//             if (dist[alp][cop] < cur.time)
//                 continue;
            
            for (int[] prob : problems) {
                int alp_req = prob[0];
                int cop_req = prob[1];
                int alp_rwd = prob[2];
                int cop_rwd = prob[3];
                int cost = prob[4];

                if (cur.alp >= alp_req && cur.cop >= cop_req) {
                    int nt = cur.time + cost;
                    int na = cur.alp + alp_rwd;
                    int nc = cur.cop + cop_rwd;
                    if ((cur.cop < COP_REQ && cur.cop < nc) || (cur.alp < ALP_REQ && cur.alp < na)) {
                        pq.offer(new HeapNode(nt, na, nc));
                    }
                }
            }
            
            if (cur.alp < ALP_REQ) {
                pq.offer(new HeapNode(cur.time + 1, cur.alp + 1, cur.cop));
            }
            if (cur.cop < COP_REQ) {
            pq.offer(new HeapNode(cur.time + 1, cur.alp, cur.cop + 1));
            }
        }
        
        return -1;
        
    }
}