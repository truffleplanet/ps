/*
뱀을 타는게 이득인 경우가 있는가?
있다. 
사다리 (3, 50)
뱀 (52, 48)
사다리 (49, 100)
이 있으면 뱀타는게 이득이다

결국에 완탐이다.
그런데 state의 수를 줄일 수 있는가?
사다리나, 뱀 모두 없다면 [1,6] 에서 가장 큰 이동 하면 되고
--> 그거 헷갈리니깐 그냥 일단 완탐 bfs로 할래 
사다리 뱀은 모두 타보면 된다.

visited[100]이 필요할까? 
적어도 먼저 온 경우가 있으면 가볼 필요 없으니깐 있으면 좋을 것 같다.
그러면 이동 횟수가 적은 순으로 
[1,6]칸 전부 채워버리면 되고,
사다리, 뱀 타고 도착한 칸에도 전부 채우면 되겠다.
구현은 순서대로 이동하면 되니 도착지점 도착시 종료하는 bfs를 하면 되겠다.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class State {
        int u;
        int dist;

        public State(int u, int dist) {
            this.u = u;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws Exception {        
        final int end = 100;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> portals = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            portals.put(u, v);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            portals.put(u, v);
        }

        Queue<State> q = new ArrayDeque<>();
        boolean[] visited = new boolean[end + 1];
        visited[1] = true;
        q.offer(new State(1, 0));

        while(!q.isEmpty()) {
            State cur = q.poll();

            if (cur.u == end) {
                System.out.println(cur.dist);
                break;
            }

            int range = Math.min(end, cur.u + 6);
            for (int i = cur.u + 1; i <= range; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    
                    if (portals.containsKey(i)) {
                        int next = portals.get(i);
                        if (!visited[next]) {
                            visited[next] = true;
                            q.offer(new State(next, cur.dist + 1));
                        }
                        continue;
                    }

                    q.offer(new State(i, cur.dist + 1));
                }
            }
        }
        
    }
}