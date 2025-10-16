/*
유니온파인드도 가능하겠지만 좀 더 빠를것같은 방법을 사용해보자
먼저 land안의 요소를 전부 순회하며 1을 만나면 bfs를 한다.
bfs를 하며 석유 덩어리 칸을 찾을건데, 이때 총 몇칸인지, 그리고 어떤 가로 범위에 포함되는지 확인하고,

해당 가로범위들의 값을 x 지점 시추시 획득 예상량 배열에 더한다.
*/

import java.util.*;

class Solution {
    
    static class Point {
        int r;
        int c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] land) {
        final int N = land.length;
        final int M = land[0].length;
        
        final int[] DR = {0, 1, -1, 0};
        final int[] DC = {1, 0, 0, -1};
        
        boolean[][] visited = new boolean[N][M];
        int[] val = new int[M]; 
        
        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j])
                    continue;
                
                visited[i][j] = true;
                
                if (land[i][j] == 0)
                    continue;
                
                q.offer(new Point(i, j));
                int l = j;
                int r = j; // [l, r]
                int cnt = 1;
                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = cur.r + DR[d];
                        int nc = cur.c + DC[d];
                        
                        if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                            continue;
                        
                        if (visited[nr][nc])
                            continue;
                        
                        if (land[nr][nc] == 0)
                            continue;
                        
                        visited[nr][nc] = true;
                        cnt++;
                        l = Math.min(l, nc);
                        r = Math.max(r, nc);
                        q.offer(new Point(nr, nc));
                    }
                } // bfs 끝
                
                // 정보 갱신 
                for (int k = l; k <= r; k++) {
                    val[k] += cnt;
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, val[i]);
        }
        
        return ans;
        
    }
}