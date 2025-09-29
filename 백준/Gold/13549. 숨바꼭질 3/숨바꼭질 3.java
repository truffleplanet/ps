import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class State {
        int pos;
        int time;

        public State (int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        final int MAX_VALUE = Math.max(N, K) * 2;
        
        Deque<State> dq = new LinkedList<>();
        dq.offerFirst(new State(N, 0));

        boolean[] visited = new boolean[MAX_VALUE + 1];
        while (!dq.isEmpty()) {
            State cur = dq.pollFirst();
            int pos = cur.pos;
            int time = cur.time;

            if (pos == K) {
                System.out.println(time);
                return;
            }

            int np1 = pos * 2;
            int np2 = pos - 1;
            int np3 = pos + 1;
            
            if (np1 <= MAX_VALUE && !visited[np1]) {
                visited[np1] = true;
                dq.offerFirst(new State(np1, time));
            }
            
            if (np2 >= 0 && !visited[np2]) {
                visited[np2] = true;
                dq.offerLast(new State(np2, time + 1));
            }

            if (np3 <= MAX_VALUE && !visited[np3]) {
                visited[np3] = true;
                dq.offerLast(new State(np3, time + 1));
            }
        }
    }
}