/*
최적의 변환규칙을 나는 못찾겠다.
따라서 
변환할 수 있는 가짓수는 10000개이니
bfs를 통한 완전 탐색을 해보기로 한다.
매 iteration에 넘겨지는 상태는, 
명령어 history, 현재 숫자 이고
visited로 재방문을 막아서, 최대 10000개의 경우만 방문하게 한다.
아 그런데. history를 String으로 관리하면서 매번 더해주는 것은 너무 비효율적으로 느껴지니
각 State가 parent State를 저장하도록 하여 관리하자. 

그러면 4개의 명령어의 동작만 잘 구현하면 끝이겠다.

전역변수 visited
bfs(State u)
bfs 내부에 4개의 동작 구현
L은 (s % 1000) * 10 + (s / 1000)
R은 (s % 10) * 1000 + (s / 10)
*/

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class State {
        State parent;
        int val;
        char CMD;

        public State(State parent, int val, char CMD) {
            this.parent = parent;
            this.val = val;
            this.CMD = CMD;
        }
        
    }

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[10000];
            Queue<State> q = new ArrayDeque<>();
            visited[A] = true;
            q.offer(new State(null, A, '\0'));

            State end = null;
            while (!q.isEmpty()) {
                State cur = q.poll();
                int val = cur.val;

                if (val == B) {
                    end = cur;
                    break;
                }

                //D
                int D_X = (val * 2) % 10000;
                int S_X = (val - 1 >= 0) ? (val - 1) : 9999;
                int L_X = (val % 1000) * 10 + (val / 1000);
                int R_X = (val % 10) * 1000 + (val / 10);

                if (!visited[D_X]) {
                    visited[D_X] = true;
                    q.offer(new State(cur, D_X, 'D'));
                } 
                if (!visited[S_X]) {
                    visited[S_X] = true;
                    q.offer(new State(cur, S_X, 'S'));
                } 
                if (!visited[L_X]) {
                    visited[L_X] = true;
                    q.offer(new State(cur, L_X, 'L'));
                } 
                if (!visited[R_X]) {
                    visited[R_X] = true;
                    q.offer(new State(cur, R_X, 'R'));
                } 
            }

            StringBuilder path = new StringBuilder();
            while (end.parent != null) {
                path.append(end.CMD);
                end = end.parent;
            }
            sb.append(path.reverse()).append('\n');
        }

        System.out.println(sb);
        
    }
}