/*
    1. 대기, 이동, 벽부수기 모두 같은 비용 소모
    2. 대기는 다음 타일이 벽이고, 현재 짝수턴 일 때만 유의미함.
    3. 최소 비용만 계산하면 됨

    bfs의 상태 표현
    r, c, k, 턴수(이걸로 홀짝 알 수 있음)
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static final int[] DR = {1, 0, -1 ,0};
    static final int[] DC = {0, 1, 0, -1};

    static final long MASK_10_BIT = 1023L;
    static final long MASK_4_BIT = 15L;
    static final long MASK_20_BIT = 1048575L;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] isWall = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                isWall[i][j] = s.charAt(j) == '1';
            }
        }
        
        int[][] visited = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        long[] q = new long[H * W * K * 2];
        int head = 0;
        int tail = 0;

        q[tail++] = encode(0, 0, 0, 1);
        visited[0][0] = 0;

        while (head < tail) {       
            long state = q[head++];
            int c = (int) (state & MASK_10_BIT);
            int r = (int) ((state >> 10) & MASK_10_BIT);
            int k = (int) ((state >> 20) & MASK_4_BIT);
            int cost = (int) ((state >> 24) & MASK_20_BIT);

            if (r == H - 1 && c == W - 1) {
                System.out.println(cost);
                return;
            }

            boolean isDay = (cost % 2 != 0);
            boolean waitAdded = false;

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                    continue;
                }

                if (isWall[nr][nc]) {
                    if (k < K && visited[nr][nc] > k + 1) {
                        if (isDay) {
                            visited[nr][nc] = k + 1;
                            q[tail++] = encode(nr, nc, k + 1, cost + 1);
                        } else {
                            if (!waitAdded) {
                                q[tail++] = encode(r, c, k, cost + 1);
                                waitAdded = true;
                            }
                        }
                    }
                } else {
                    if (visited[nr][nc] > k) {
                        visited[nr][nc] = k;
                        q[tail++] = encode(nr, nc, k, cost + 1);
                    }
                }
            }
        }
        System.out.println("-1");
    }

    static long encode(int r, int c, int k, int cost) {
        long state = 0L;
        state |= (long) c;
        state |= (long) r << 10;
        state |= (long) k << 20;
        state |= (long) cost << 24;
        return state;
    }
}