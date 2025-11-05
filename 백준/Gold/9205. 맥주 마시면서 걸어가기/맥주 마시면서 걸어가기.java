/*
1. 최대 이동거리는 50*20 ==> 1000
2. 이를 이용해 인접 리스트로 그래프를 그린다.
3. bfs로 시작점부터 출발점까지 이어져 있는지 확인한다.
O(V + E)인데 E가 V^2이므로 100^2
거리 계산에 V^2
테케 50
1,000,000
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int dist(Position o) {
            return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
        }
    }
    
    public static void main(String[] args) throws Exception {
        final String WIN = "happy";
        final String LOSE = "sad";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0 ; t < T; t++) {
            int N = Integer.parseInt(br.readLine()) + 2;
            Position[] pos = new Position[N];
            for (int i = 0; i < N ; i++) {
                st = new StringTokenizer(br.readLine());
                Position p = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                pos[i] = p;
            }

            List<Integer>[] G = new List[N];
            for (int i = 0; i < N; i++) {
                G[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (pos[i].dist(pos[j]) <= 1000) {
                        G[i].add(j);
                        G[j].add(i);
                    }
                }
            }

            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N];
            visited[0] = true;
            q.offer(0);

            boolean reach = false;

            while (!q.isEmpty()) {
                int cur = q.poll();

                if (cur == N - 1) {
                    reach = true;
                    break;
                }
                
                for (int v : G[cur]) {
                    if (v == N - 1) {
                        reach = true;
                        break;
                    }

                    if (!visited[v]) {
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }

            if (reach) {
                sb.append(WIN).append('\n');
            } else {
                sb.append(LOSE).append('\n');
            }
            
        }

        System.out.println(sb);

        
    }
}