import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static int[] DR = {1, 0, -1, 0};
    final static int[] DC = {0, 1, 0, -1};

    static int H;
    static int W;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        boolean[][] isWall = new boolean[H][W];
        int[][] getGroupId = new int[H][W];
        List<Integer> groupCnt = new ArrayList<>();
        groupCnt.add(-1);
        
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                isWall[i][j] = (s.charAt(j) == '1') ? true : false; 
            }
        }

        // 빈 공간 군집화 계산
        int AUTO_INCREMENT_ID = 1;
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (isWall[i][j] || getGroupId[i][j] != 0) {
                    continue;
                }
                getGroupId[i][j] = AUTO_INCREMENT_ID++;
                q.offer(getState(i, j));
                int cnt = 1;
                while (!q.isEmpty()) {
                    int state = q.poll();
                    int r = getR(state);
                    int c = getC(state);

                    for (int d = 0; d < 4; d++) {
                        int nr = r + DR[d];
                        int nc = c + DC[d];

                        if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                            continue;
                        }
                        if (isWall[nr][nc]) {
                            continue;
                        }
                        if (getGroupId[nr][nc] != 0) {
                            continue;
                        }

                        getGroupId[nr][nc] = getGroupId[i][j];
                        cnt++;
                        q.offer(getState(nr, nc));
                    }
                }
                groupCnt.add(cnt % 10);
            }
        }

        q.clear();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!isWall[i][j]) {
                    sb.append('0');
                    continue;
                }
                int cnt = 1;
                HashSet<Integer> visited = new HashSet<>();
                visited.add(0);
                for (int d = 0; d < 4; d++) {
                    int nr = i + DR[d];
                    int nc = j + DC[d];
                    
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        continue;
                    }
                    
                    int groupId = getGroupId[nr][nc];
                    if (visited.contains(groupId)) {
                        continue;
                    }
                    visited.add(groupId);
                    cnt += groupCnt.get(groupId);
                }
                cnt %= 10;
                sb.append(cnt);
            }
            
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int getState(int r, int c) {
        return r * W + c;
    }

    private static int getR(int state) {
        return state / W;
    }
    
    private static int getC(int state) {
        return state % W;
    }
}