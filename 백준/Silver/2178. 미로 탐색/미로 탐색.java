import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class State {
        int r;
        int c;
        int dist;

        public State (int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws Exception {

        final int[] DR = {0, 1, -1 ,0};
        final int[] DC = {1, 0, 0, -1};
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        boolean[][] grid = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                grid[i][j] = (line[j] == '1') ? true : false;
            }
        }

        Queue<State> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        q.offer(new State(0, 0, 1));

        while(!q.isEmpty()) {
            State cur = q.poll();
            
            int nd = cur.dist + 1;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (nr == N - 1 && nc == M - 1) {
                    System.out.println(nd); // N,M은 2보다 크므로 시작-목적기가 같을 수 없음.
                    return; // 따라서 이렇게만 정답 출력하면 반드시 정답 출력함.
                }

                if (grid[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new State(nr, nc, nd));
                }

                
            }
        
        } // 정답이 없으면 출력 없이 종료됨.
        
        
    }
}