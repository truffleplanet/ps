/*
조건:
완전 연결 그래프에 가까워질 수 있음.

풀이 아이디어:
최적 경유지까지 갈때는 당연히 출발 지점에서 거기까지 최소 거리로 가야함.
그리고 경유지에서 각각 a, b로 가는 비용은 그 지점부터 a, b까지 최소여야함.

이는 출발지에서 a까지 최소 거리, b까지 최소거리 구하는 것과 다르다.

경유지 개념이면 플로이드 워셜이 괜찮지 않을까

1. 플로이드 워셜로 모든 노드간 최소거리를 구한다. => 200^3
2. 출발지에서 임의 경유지 가는 거리 + 그 노드에서 a, b가는 거리 중 최소를 구한다. => 200
1 + 2 => 
*/

import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int INF = 100_000_000;
        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];
            dist[u][v] = w;
            dist[v][u] = w;
        }
        
        for (int m = 1; m <= n; m++) {
            for (int u = 1; u <= n; u++) {
                for (int v = 1; v <= n; v++) {
                    int nd = dist[u][m] + dist[m][v];
                    if (dist[u][v] > nd) {
                        dist[u][v] = nd;
                    }
                }
            }
        }
        int ans = INF;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dist[s][i] + dist[i][a] + dist[i][b]);
        }
    
        return ans;
    }
}