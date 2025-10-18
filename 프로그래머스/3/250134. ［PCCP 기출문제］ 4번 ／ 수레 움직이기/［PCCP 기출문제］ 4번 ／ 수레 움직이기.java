/*
벽이나 격자 이동 불가.
자신이 방문했던 칸으로 이동 불가
도착하면 움직이지 않음
동시에 두 수레 같은 칸으로 이동 불가
수레 위치 스왑 불가 


빨 파 수레를 합쳐서 상태로 넘겨서 
위 조건을 모두 만족하게 bfs하고
둘다 도착하면 끝나도록 구현.

방문 기록은 clone의 편의성을 위해 flatten해서 넘겨주기. (r * m + c)

*/

import java.util.*;

class Point {
    int r;
    int c;
    
    Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    boolean equals(Point b) {
        return this.r == b.r && this.c == b.c;
    }
    
}

class State {
    Point red;
    Point blue;
    boolean[] red_hist;
    boolean[] blue_hist;
    int dist;
    
    public State(Point red, Point blue, boolean[] red_hist, boolean[] blue_hist, int dist) {
        this.red = red;
        this.blue = blue;
        this.red_hist = red_hist;
        this.blue_hist = blue_hist;
        this.dist = dist;
    }
}


class Solution {
    
    Point RED_GOAL;
    Point BLUE_GOAL;
    int[] DR = {-1, 0, 1, 0};
    int[] DC = {0, -1, 0, 1};
    int N;
    int M;
    int CELLS;
    int[][] grid;
        
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        CELLS = N * M;
        grid = maze;
        
        Point r = null;
        Point b = null;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int x = maze[i][j];
                if (x == 1) {
                    r = new Point(i, j);
                } else if (x == 2) {
                    b = new Point(i, j);
                } else if (x == 3) {
                    RED_GOAL = new Point(i, j);
                } else if (x == 4) {
                    BLUE_GOAL = new Point(i, j);
                }
            }
        }
        
        boolean[] red_hist = new boolean[CELLS];
        boolean[] blue_hist = new boolean[CELLS];
        red_hist[key(r.r, r.c)] = true;
        blue_hist[key(b.r, b.c)] = true;
        
        State start = new State(r, b, red_hist, blue_hist, 0);
        
        return bfs(start);
    }
    
    private int key(int r, int c) {
        return r * M + c;
    }
    
    private boolean boundcheck(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M; 
    }
    
    private int bfs(State start) {
        Queue<State> q = new ArrayDeque<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
            State cur = q.poll();
            Point red = cur.red;
            Point blue = cur.blue;
            boolean[] red_hist = cur.red_hist;
            boolean[] blue_hist = cur.blue_hist;
            
            int dist = cur.dist;
            
            if (red.equals(RED_GOAL) && blue.equals(BLUE_GOAL))
                return dist;
            
            
            
            for (int i = 0; i < 4; i++) {
                int red_nr = red.r;
                int red_nc = red.c;
                boolean[] new_red_hist = red_hist;
                Point next_red = red;
                
                if (!red.equals(RED_GOAL)) {
                    red_nr = red.r + DR[i];
                    red_nc = red.c + DC[i];
                    
                    if (!boundcheck(red_nr, red_nc)) // 경계 체크
                        continue;
                    
                    if (grid[red_nr][red_nc] == 5) // 벽 체크
                        continue;
                    
                    if (red_hist[key(red_nr, red_nc)]) // 방문 여부 체크 
                        continue;
                    
                    new_red_hist = red_hist.clone();
                    new_red_hist[key(red_nr, red_nc)] = true;
                    next_red = new Point(red_nr, red_nc);
                    } 

                for (int j = 0; j < 4; j++) {
                    int blue_nr = blue.r;
                    int blue_nc = blue.c;
                    boolean[] new_blue_hist = blue_hist;
                    Point next_blue = blue;

                    if (!blue.equals(BLUE_GOAL)) {
                        blue_nr = blue.r + DR[j];
                        blue_nc = blue.c + DC[j];

                        if (!boundcheck(blue_nr, blue_nc))
                            continue;

                        if (grid[blue_nr][blue_nc] == 5)
                            continue;

                        if (blue_hist[key(blue_nr, blue_nc)])
                            continue;
                        
                        if (blue_nr == red_nr && blue_nc == red_nc) // 충돌 방지
                            continue;
                        
                        if (blue_nr == red.r && blue_nc == red.c && red_nr == blue.r && red_nc == blue.c) // 스왑 방지
                            continue;

                        new_blue_hist = blue_hist.clone();
                        new_blue_hist[key(blue_nr, blue_nc)] = true;
                        next_blue = new Point(blue_nr, blue_nc);
                    } else {
                        if (blue_nr == red_nr && blue_nc == red_nc) // 충돌 방지
                            continue;
                        
                        if (blue_nr == red.r && blue_nc == red.c && red_nr == blue.r && red_nc == blue.c) // 스왑 방지
                            continue;

                    }
                    
                    // 두 수레 중 하나라도 이동을 못하는 경우를 고려해야함. 
                    
                    if((!next_blue.equals(blue) || blue.equals(BLUE_GOAL)) && (!next_red.equals(red) || red.equals(RED_GOAL))) { // 둘 중 하나라도 이동했다면
                        q.offer(new State(next_red, next_blue, new_red_hist, new_blue_hist, dist + 1));
                    }
                }
            }
        }
        
        return 0;
        
    }

}