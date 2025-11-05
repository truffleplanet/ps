/*
치즈 내부라는 조건 고려해야 함. 

외부 공기는 외부에서 bfs하면 모두 판별 가능함
외부 공기가 퍼져나가다가, 치즈있으면 cheese에 count+ 시켜둔다.

cheese에 count가 +2 이상이면.. 삭제할 리스트에 추가한다.

*/


import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    
    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    final static int[] DR = {-1, 1, 0, 0};
    final static int[] DC = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N + 2][M + 2];
        int total = 0; 
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int v = Integer.parseInt(st.nextToken());
                grid[i][j] = v;
                if (v == 1)
                    total++;
            }
        }

        int time = 0;
        while (total > 0) {
            List<Point> lst = new ArrayList<>();
            int[][] cheeseCount = new int[N + 2][M + 2];
            boolean[][] visited = new boolean[N + 2][M + 2];
            visited[0][0] = true;
            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(0, 0));

            while (!q.isEmpty()) {
                Point cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + DR[d];
                    int nc = cur.c + DC[d];

                    if (nr < 0 || nr >= N + 2 || nc < 0 || nc >= M + 2)
                        continue;
                    
                    if (visited[nr][nc])
                        continue;

                    if (grid[nr][nc] == 1) {
                        cheeseCount[nr][nc]++;
                        if (!visited[nr][nc] && cheeseCount[nr][nc] >= 2) {
                            visited[nr][nc] = true;
                            lst.add(new Point(nr, nc));
                        }
                        continue;
                    }

                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
            
            if (lst.isEmpty())
                break;

            time++;
            for (Point p : lst) {
                grid[p.r][p.c] = 0;
                total--;
            }
            
        }

        System.out.println(time);
        
    }
}