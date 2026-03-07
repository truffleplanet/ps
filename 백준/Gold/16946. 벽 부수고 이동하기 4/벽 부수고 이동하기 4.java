import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static int[] DR = {1, 0, -1, 0};
    final static int[] DC = {0, 1, 0, -1};

    static int H, W;
    static boolean[][] isWall;
    static int[][] groupIdMap;
    static List<Integer> groupSize;
    static int[] q;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        isWall = new boolean[H][W];
        groupIdMap = new int[H][W];
        q = new int[H * W];
        groupSize = new ArrayList<>();
        groupSize.add(-1);
        
        for (int i = 0; i < H; i++) {
            String s = br.readLine();
            for (int j = 0; j < W; j++) {
                isWall[i][j] = (s.charAt(j) == '1');
            }
        }

        // 빈 공간 군집화 계산
        int currentGroupId = 1;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (isWall[i][j] || groupIdMap[i][j] != 0) {
                    continue;
                }
                int size = bfs(i, j, currentGroupId);
                groupSize.add(size % 10);
                currentGroupId++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!isWall[i][j]) {
                    sb.append('0');
                } else {
                    sb.append(countMovable(i, j) % 10);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int bfs(int startR, int startC, int id) {
        int head = 0;
        int tail = 0;
        
        q[tail++] = (startR << 10) | startC;
        groupIdMap[startR][startC] = id;
        
        int cnt = 1;
        while (head < tail) {
            int state = q[head++];
            int r = state >> 10;
            int c = state & 1023;

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                    continue;
                }
                if (isWall[nr][nc] || groupIdMap[nr][nc] != 0) {
                    continue;
                }
                groupIdMap[nr][nc] = id;
                cnt++;
                q[tail++] = (nr << 10) | nc;
            }
        }
        return cnt;
    }

    private static int countMovable(int r, int c) {
        int total = 1;
        int[] seenIds = new int[4];
        int seenCount = 0;

        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d];
            int nc = c + DC[d];

            if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                continue;
            }

            if (isWall[nr][nc]) {
                continue;
            }

            int groupId = groupIdMap[nr][nc];
            boolean isDuplicate = false;
            for (int i = 0; i < seenCount; i++) {
                if (seenIds[i] == groupId) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                seenIds[seenCount++] = groupId;
                total += groupSize.get(groupId);
            }
        }
        return total;
    }
}