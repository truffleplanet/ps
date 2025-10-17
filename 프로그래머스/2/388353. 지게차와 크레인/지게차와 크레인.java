import java.util.*;

class Solution {
    
    int N;
    int M;
    char[][] container;
    boolean[][] isEmpty;
    
    final int[] DR = {0, 1, -1, 0};
    final int[] DC = {1, 0, 0, -1};
    
    class Point {
        int r;
        int c;
        
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        
        N = storage.length;
        M = storage[0].length();
        container = new char[N][M];
        isEmpty = new boolean[N][M];
        
        int count = N * M;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                container[i][j] = storage[i].charAt(j);
            }
        } 
          
        for (String cmd : requests) {

            char req = cmd.charAt(0);

            if (cmd.length() == 2) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (container[i][j] == req && !isEmpty[i][j]) {
                            isEmpty[i][j] = true;
                            count--;
                        }
                    }
                }
                continue;
            } // 크레인 동작 구현 완료 
            
            // 들어온 순간 접근 가능한 것만 꺼내면 되므로, isEmpty 업데이트는 마지막에 한번에 해야함. 
            List<Point> toRemove = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                                        
                    if (container[i][j] == req) {
                        Point np = new Point(i, j);
                        if (!isEmpty[i][j] && bfs(np)) {
                            count--;
                            toRemove.add(np);
                        }
                    }
                }
            }
        
            
            for (Point p : toRemove) {
                isEmpty[p.r][p.c] = true;
            }
            
        }
        
  
        return count;
    }
    
    boolean bfs(Point p) {
        
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[p.r][p.c] = true;
        q.offer(p);
        
        
        while (!q.isEmpty()) {
            Point cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            
            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    return true;
                
                if (isEmpty[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
        }
        
        return false;
    }
    
}