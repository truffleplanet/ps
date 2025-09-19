import java.util.*;

class Solution {
    
    static class Node {
        int r;
        int c;
        
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        final int[] DR = {-1, 0, 1, 0};
        final int[] DC = {0, 1, 0, -1};
        
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        // logic
        boolean[][] visited = new boolean[m][n];
        Queue<Node> q = new ArrayDeque<>();
        
        for (int i =0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || picture[i][j] == 0)
                    continue;
               
                visited[i][j] = true;
                numberOfArea++;
                int localArea = 1;
                q.offer(new Node(i, j));
                while (!q.isEmpty()) {
                    Node cur = q.poll();
                    int r = cur.r;
                    int c = cur.c;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + DR[d];
                        int nc = c + DC[d];
                        
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                            continue;
                        
                        if (visited[nr][nc] || picture[nr][nc] == 0)
                            continue;
                        
                        if (picture[r][c] == picture[nr][nc]) {
                            visited[nr][nc] = true;
                            localArea++;
                            q.offer(new Node(nr, nc));
                        }
                    }
                }
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, localArea);
            }
        }
        
        
        

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}