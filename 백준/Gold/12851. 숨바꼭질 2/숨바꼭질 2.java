/*
일반적인 중복탐색없는 bfs를 하면, 경로의 수를 찾을 수 없음.
그렇다고, 모든 중복을 허용하면 무한 루프에 빠진다.

1. 
적어도 각 경로는 자신이 왔던 곳에 다시 가면 안된다.

2. 
어떤 경유지에 현재 상태보다 더 빨리 도달할 수 있는 상태가 있다면
그 상태는 최단 경로 상태일 수 없다.


*/

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class State {
        int r;
        int d;

        public State(int r, int d) {
            this.r = r;
            this.d = d;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 1. 최초 방문보다 더 빠르게 올 수 있는 경우는 없음
        // 2. 그렇다고, 같은 시간 내에 올 수 있는 노드가 없는 건 아님
        final int MAX = 140_000; // 7만일 때는 2배하고 10만으로 내려오는 전략이 더 느리다. 6만까지는 더 빠르고.적당한 최대 위치
        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<State> q = new ArrayDeque<>();
        dist[N] = 0;
        q.offer(new State(N, 0));
        int cnt = 0;
        
        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.r == K) {
                if (cur.d == dist[K]) {
                    cnt++;
                }
                continue;
            }

            if (cur.d > dist[K]) {
                break;
            }
            
            int nd = cur.d + 1;
            
            int[] nexts = new int[] {cur.r + 1, cur.r - 1, cur.r * 2};
            for (int nx : nexts) {
                if (nx < 0 || nx >= MAX)
                    continue;

                if (dist[nx] >= nd) {
                    dist[nx] = nd;
                    q.offer(new State(nx, nd));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dist[K]).append('\n').append(cnt);
        System.out.println(sb);
    }
}