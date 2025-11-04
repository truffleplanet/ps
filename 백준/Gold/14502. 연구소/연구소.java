/*
어디가 최선의 선택인지 데이터읽고 알 방법이 있나?
나는 잘 모르겠다.
0 빈칸
1 벽
2 바이러스

그러면, 가장 단순하게는
1. 모든 위치 중 3곳을 뽑는다.
2. 그 위치에 벽 놓은 채로 바이러스를 bfs로 퍼트린다
3. 안전영역의 크기를 구한다

3은 2의 과정에 통합할 수 있고
최대 8x8의 맵이고

64C3 * 64 --> 41664 * 64 = 2,666,496
충분히 2초 통과 가능하다.

위치 중 3곳을 뽑는 동작은 어떻게 할까?
dfs 루프를 돌며!

dfs의 파라미터는 (depth, start) 
(r * M + c + 1) 값을 start로 넘겨주며 진행.
dfs는 반환값은 따로 없고, depth가 3에 도달했을 때, bfs함수에서 값을 받아와서 최대값 갱신한다.
*/

import java.util.*;
import java.lang.*;
import java.io.*;
    
// The main method must be in a class named "Main".
class Main {

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    final static int[] DR = {-1, 1, 0, 0};
    final static int[] DC = {0, 0, -1, 1};

    static int ans;
    static int N;
    static int M;
    static int[][] grid;
    static int zeros;
    static ArrayDeque<Node> viruses;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        zeros = 0;
        viruses = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                grid[i][j] = v;
                if (v == 0)
                    zeros++;
                else if (v == 2) 
                    viruses.offer(new Node(i, j));
            }
        }
        
        ans = 0;
        dfs(0, 0);
        System.out.println(ans - 3);
    }

    public static void dfs(int depth, int start) {
        if (depth == 3) {
            ans = Math.max(ans, bfs());
            return;
        }

        int P = start / M;
        int Q = start % M;
        for (int j = Q; j < M; j++) {
            if (grid[P][j] == 0) {
                grid[P][j] = 1;
                dfs(depth + 1, P * M + j);
                grid[P][j] = 0;
            }
        }

        for (int i = P + 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    dfs(depth + 1, i * M + j);
                    grid[i][j] = 0;
                }
            }
        }
    }

    public static int bfs() {
        int total = 0;
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> q = (ArrayDeque<Node>) viruses.clone();

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (visited[nr][nc])
                    continue;

                if (grid[nr][nc] != 0)
                    continue;

                visited[nr][nc] = true;
                total++;
                q.offer(new Node(nr, nc));
            }
        }

        return zeros - total;
        
    }
}