/*

예시: 10의 경우에 10 → 9 → 3 → 1 로 3번 만에 만들 수 있다.
예시에서 알 수 있듯이 나눌 수 있다고 나누는 그리디 접근은 정답을 보장하지 않는다.

따라서 boolean[] visited만들고
bfs로 서치한다.

상태는 최대 10^6 즉 백만이므로
1억번 연산을 1초에 한다고 가정하면
0.01초에 근사한 시간에 풀 수 있을 것이다.
*/


import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class State {
        int val;
        int dist;

        public State(int val, int dist) {
            this.val = val;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }
        
        boolean[] visited = new boolean[N];
        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(N, 0));
        
        while (!q.isEmpty()) {
            State state = q.poll();
            int cur = state.val;
            int nd = state.dist + 1;
            
            int v1 = cur - 1;
            int v2 = (cur % 2 == 0) ? (cur / 2) : v1;
            int v3 = (cur % 3 == 0) ? (cur / 3) : v1;

            int[] vals = new int[] {v1, v2, v3};

            for (int v : vals) {
                if (v == 1) {
                    System.out.println(nd);
                    return;
                }
                
                if (!visited[v]) {
                    visited[v] = true;
                    q.offer(new State(v, nd));
                }
                
            }

        }
        
    }
}