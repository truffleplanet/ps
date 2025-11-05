/*
물고기의 상태
1. 먹은 물고기의 수
2. 크기
3. 위치
4. 시간

풀이 1.
1. bfs를 하는데, visited를 물고기 먹을 때 마다 초기화 해주어야 함.
2. 먹을 수 있는 물고기가 나오면 정지
3. 마지막 상태만 남기고 나머지 상태는 버림.
4. 정답 시간 갱신.
5. 다시 그 상태부터 bfs

풀이 2. 
1. 모든 물고기의 위치와 크기 기록.
2. 매 step별로, 크기 조건 충족하는 모든 물고기와 맨해튼 거리 계산 
3. 가까운 순, 가장 위에 이쓴ㄴ 순, 왼쪽에 있는 순으로 정렬 후, 하나씩 도달가능성 확인
4. 도달가능성 확인되면 이동

풀이 1로 해보기로 한다. 

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class State implements Comparable<State>{
        int r;
        int c;
        int time;

        public State(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        @Override
        public int compareTo(State o) {
            int v1 = Integer.compare(this.time, o.time);
            if (v1 != 0)
                return v1;
            int v2 = Integer.compare(this.r, o.r);
            if (v2 != 0)
                return v2;
            return Integer.compare(this.c, o.c);
        }
    }

    // 상, 좌, 우, 하 순으로 이동하면
    // 가장 위에 있는 것, 먼저 찾고, 그다음에 가장 좌로
    // 이것만으로는 위칸에서, 아래칸에서 등 조건 충족 못함.
    final static int[] DR = {-1, 0, 0, 1};
    final static int[] DC = {0, -1, 1, 0};

    static int N;
    static int[][] grid;
    static int ans;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        int startR = 0;
        int startC = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 9) {
                    startR = i;
                    startC = j;
                } else {
                    grid[i][j] = v;
                }
            }
        }

        ans = 0;
        bfs(new State(startR, startC, 0), 0, 2);
        System.out.println(ans);
    }

    public static void bfs(State start, int cnt, int size) {
        boolean[][] visited = new boolean[N][N];
        visited[start.r][start.c] = true;

        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(start);

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (grid[cur.r][cur.c] < size && grid[cur.r][cur.c] > 0) {
                cnt++;
                grid[cur.r][cur.c] = 0;
                State next = new State(cur.r, cur.c, cur.time);
                ans = Math.max(ans, cur.time);
                if (cnt >= size) {
                    cnt = 0;
                    size++;
                }
                bfs(next, cnt, size);
                return;
            }
            
            int nTime = cur.time + 1;
            
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                if (visited[nr][nc])
                    continue;

                if (grid[nr][nc] > size) {
                    continue;
                }

                if (grid[nr][nc] <= size) {
                    visited[nr][nc] = true;
                    q.offer(new State(nr, nc, nTime));
                }
            }
        }
    }
}