import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Point {
        int r, c;
        public Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    final static int[] DR = {-1, 1, 0, 0};
    final static int[] DC = {0, 0, -1, 1};

    static int H, W;
    static char[][] grid;
    static boolean[][] visited;
    static int keys;
    static int docCount;

    static ArrayList<Point>[] waitingList;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            grid = new char[H + 2][W + 2];
            Arrays.fill(grid[0], '.');
            Arrays.fill(grid[H + 1], '.');
            for (int i = 1; i < H + 1; i++) {
                grid[i][0] = '.';
                grid[i][W + 1] = '.';
            }

            for (int i = 1; i < H + 1; i++) {
                String row = br.readLine();
                for (int j = 1; j < W + 1; j++) {
                    grid[i][j] = row.charAt(j - 1);
                }
            }

            keys = 0; 
            for (char ch : br.readLine().toCharArray()) {
                keys |= (1 << (ch - 'a'));
            }

            // init 
            visited = new boolean[H + 2][W + 2];
            waitingList = new ArrayList[26];
            for (int i = 0; i < 26; i++) waitingList[i] = new ArrayList<>();
            docCount = 0;

            bfs();
            sb.append(docCount).append('\n');
        }

        System.out.println(sb);
        
    }

    public static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (nr < 0 || nr >= H + 2 || nc < 0 || nc >= W + 2)
                    continue;
                if (grid[nr][nc] == '*' || visited[nr][nc])
                    continue;

                char ch = grid[nr][nc];

                if (ch == '.') {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                } else if (ch == '$') {
                    docCount++;
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                } else if (ch >= 'A' && ch <= 'Z') {
                    int doorNum = ch - 'A';
                    if ((keys & (1 << doorNum)) != 0) {
                        visited[nr][nc] = true;
                        q.offer(new Point(nr, nc));
                    } else {
                        visited[nr][nc] = true;
                        waitingList[doorNum].add(new Point(nr, nc));
                    }
                } else if (ch >= 'a' && ch <= 'z') {
                    int keyNum = ch - 'a';
                    keys |= (1 << keyNum);

                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));

                    if (!waitingList[keyNum].isEmpty()) {
                        for (Point p : waitingList[keyNum]) {
                            q.offer(p);
                        }
                    }
                }
            }
        }
    }
    
}