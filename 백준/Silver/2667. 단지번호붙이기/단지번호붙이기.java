import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static boolean[][] visited;
    static int N;
    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, -1, 0, 1};

    static class State {
        int r, c;

        public State(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (row[j] == '0') {
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }

        int total = 0;
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    total++;
                    counts.add(bfs(i, j));
                }
            }
        }


        sb.append(total).append('\n');
        Collections.sort(counts);
        for (int count : counts) {
            sb.append(count).append('\n');
        }

        System.out.println(sb);
        
    }

    static int bfs(int r, int c) {
        Queue<State> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new State(r, c));
        
        int count = 1;
        while(!q.isEmpty()) {
            State cur = q.poll();
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >=N)
                    continue;

                if (visited[nr][nc])
                    continue;

                visited[nr][nc] = true;
                count++;
                q.offer(new State(nr, nc));
            } 
        }

        return count;
    }
}