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

            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
        
    }

    public static long dfs(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        if (r == N - 1 && c == N - 1) {
            return 1;
        }
        
        int d = board[r][c];
        if (d == 0) {
            return 0;
        }

        long cnt = 0;
        
        int nr = r + d;
        if (nr < N) {
            cnt += dfs(nr, c);
        } 

        int nc = c + d;
        if (nc < N) {
            cnt += dfs(r, nc);
        }
        
        dp[r][c] = cnt;
        return dp[r][c];
    }
}