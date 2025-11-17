/*
시작에서 전이 가능한 상태들은 정해져있고 dag 형태로 뻗어나간다.

dfs를 하면서, 도달할 때마다 return 1? 하면 완전탐색이라서 시간 초과가 되고

경로가 결국 트리 형태이고, 마지막 leaf노드가 오른쪽 맨 아래인 경우들은 전부 도달할 수 있으니
tree dp로 생각할 수 있다.

top down dp로 구성해보자.
*/


import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N;
    static int[][] board;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
        
    }

    public static long dfs(int r, int c) {

        if (r == N - 1 && c == N - 1) {
            return 1;
        }
        
        int d = board[r][c];
        if (d == 0) {
            return 0;
        }

        int nr = r + d;
        int nc = c + d;
        long cnt = 0;
        if (nr < N) {
            if (dp[nr][c] != 0) {
                cnt += dp[nr][c];
            } else {
                cnt += dfs(nr, c);
            }
        } 

        if (nc < N) {
            if (dp[r][nc] != 0) {
                cnt += dp[r][nc];
            } else {
                cnt += dfs(r, nc);
            }
        }
        
        dp[r][c] = cnt;
        return dp[r][c];
    }
}