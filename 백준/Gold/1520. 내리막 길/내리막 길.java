/*
낮은 곳에서 다시 높은 곳으로 어떻게 해도 갈 수 없음
점프와 마찬가지로 트리형태로 생각할 수 있는 경로이다. 
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static int[] DR = {1, -1, 0, 0};
    final static int[] DC = {0, 0, 1, -1};

    static int[][] grid;
    static int H;
    static int W;
    
    static long[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        grid = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        System.out.println(dfs(0, 0));
    }

    public static long dfs(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        
        if (r == H - 1 && c == W - 1) {
            return 1;
        }

        long count = 0;

        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d];
            int nc = c + DC[d];

            if (nr < 0 || nr >= H || nc < 0 || nc >= W)
                continue;

            if (grid[nr][nc] < grid[r][c]) {
                count += dfs(nr, nc);
            }
        }

        dp[r][c] = count;
        return dp[r][c];
    }
}