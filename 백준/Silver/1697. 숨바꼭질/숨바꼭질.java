// 65000을 기준으로 그냥 가면 35000번인데 *2 돌아오는건 30000이니 여긴 두배 해야하고
// 70000 기준으로는 가는거 30000 *2하고 돌아오면 40000이니 
// 다충 140,000 정도로 최종사이즈 잡으면 여유 있을 것임.

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class State {
        int loc;
        int dist;

        public State(int loc, int dist) {
            this.loc = loc;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws Exception {
        final int MAX = 140_000;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        if (N == K) {
            System.out.println(0);
            return;
        }

        Queue<State> q = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX];
        visited[N] = true;
        q.offer(new State(N, 0));

        while (!q.isEmpty()) {
            State cur = q.poll();
            int loc = cur.loc;
            int nd = cur.dist + 1;
            
            int nx1 = loc + 1;
            int nx2 = loc - 1;            
            int nx3 = loc * 2;

            if (nx1 == K || nx2 == K || nx3 == K) {
                System.out.println(nd);
                return;
            }

            if (nx1 >= 0 && nx1 < MAX && !visited[nx1]) {
                visited[nx1] = true;
                q.offer(new State(nx1, nd));
            }

            if (nx2 >= 0 && nx2 < MAX && !visited[nx2]) {
                visited[nx2] = true;
                q.offer(new State(nx2, nd));
            }

            if (nx3 >= 0 && nx3 < MAX && !visited[nx3]) {
                visited[nx3] = true;
                q.offer(new State(nx3, nd));
            }

        }
    }
}