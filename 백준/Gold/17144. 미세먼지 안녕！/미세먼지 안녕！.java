/*
공기청정기는 항상 1번열. 

1. 공기청정기 순환 움직임 구현해야함. 
--> 한칸씩 이동시키기 

2. 미세먼지 확산 구현해야함.
--> 모든 위치에서 확산을 하는데 이걸 서로 독립적으로 해야함 (6 * 50)
--> 매번 새로 만들기 (6 * 50)
--> 공기청정기가 있는 칸으로는 확산 x

max(T) == 1000 으로 시간 복잡도 ok

*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] DR = {1, -1, 0, 0};
    static int[] DC = {0, 0, 1, -1};
    static int[][] ORDER = {{2, 1, 3, 0}, {2, 0, 3, 1}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] grid = new int[R][C];
        int conditionerR = -1;
        
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int v = Integer.parseInt(st.nextToken());
                grid[i][j] = v;
                if (v == -1 && conditionerR == -1) {
                    conditionerR = i;
                }
            }
        }

        // 동작
        for (int t = 0; t < T; t++) {

            // 1. 미세먼지 확산.
            int[][] sums = new int[R][C];
            
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    
                    int f = grid[i][j] / 5;
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = i + DR[d];
                        int nc = j + DC[d];

                        if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                            continue;

                        if (grid[nr][nc] == -1)
                            continue;

                        sums[nr][nc] += f;
                        grid[i][j] -= f;
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (grid[i][j] == -1)
                        continue;
                    grid[i][j] += sums[i][j];
                }
            }
            
            // 2. 공기청정기 작동
            int r = conditionerR;
            int c = 1;
            int prev = 0;
            int order = 0;
            while (grid[r][c] != -1) {
                int next = grid[r][c];
                grid[r][c] = prev;
                prev = next;

                int nr = r + DR[ORDER[0][order]];
                int nc = c + DC[ORDER[0][order]];
                
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    order++;
                    nr = r + DR[ORDER[0][order]];
                    nc = c + DC[ORDER[0][order]];
                }
                
                r = nr;
                c = nc;
            }

            r = conditionerR + 1;
            c = 1;
            prev = 0;
            order = 0;
            while (grid[r][c] != - 1) {
                int next = grid[r][c];
                grid[r][c] = prev;
                prev = next;
                
                int nr = r + DR[ORDER[1][order]];
                int nc = c + DC[ORDER[1][order]];
                
                if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
                    order++;
                    nr = r + DR[ORDER[1][order]];
                    nc = c + DC[ORDER[1][order]];
                }
                r = nr;
                c = nc;
            }
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] != -1) {
                    ans += grid[i][j];
                }
            }
        }

        System.out.println(ans);
    }
}