/*
풀이: 
1. 
다리 길이는 최소 2이다.
섬끼리 잇는다는 생각을 하지 말고, 빈 공간에서 시작해보자
열방향으로 한번, 행 방향으로 한번 읽으면서 길이가 2이상이고 양 끝이 섬이면 다리이다.
즉 전체 그림을 무향 가중치 그래프로 생각할 수 있다.

2.
처음에는 섬의 번호가 매겨져있지 않기 때문에, 섬의 번호부터 매긴다.
차례대로 배열 전부 읽으며, 섬 만나면 bfs하면, 섬 번호를 매길 수 있다. 

3.
최소 비용으로 모두 연결하는 것이고,
현재 섬의 개수는 최대 6으로 다리개수보다 일반적으로 작을테니
prim's mst를 사용하기로 한다.
만약 mst가 형성되지 않는다면 -1, 
된다면 mst의 비용을 출력하기로 한다.

*/


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// The main method must be in a class named "Main".
public class Main {
    static class Edge implements Comparable<Edge> {
        int to;
        int c;

        public Edge(int to, int c) {
            this.to = to;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static final int EMPTY = 0;
    static final int UNVISITED = 1;
    static final int[] DR = {0, 0, 1, -1};
    static final int[] DC = {1, -1, 0, 0};

    static int H, W;
    static int mapNum;
    static int[][] grid;
    static List<Edge>[] G;

    public static void main(String[] args) throws Exception{
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

        mapNum = 2;
        Queue<Point> q = new ArrayDeque<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (grid[i][j] == UNVISITED) {
                    bfs(q, i, j);
                }
            }
        }

        int V = mapNum - 2; // mapNum은 2에서 시작함.
        G = new List[mapNum]; // 빈칸 두고 사용하기
        for (int i = 2; i < mapNum; i++) {
            G[i] = new ArrayList<>();
        }

        // 열 방향 읽기
        // 상태기계: start == -1, cost = 0, 이 시작점.
        for (int i = 0; i < H; i++) {
            int j = 0;
            int start = -1;
            int cost = 0;
            while (j < W) {
                if (grid[i][j] >= 2) {
                    if (start == -1) {
                        start = grid[i][j];
                    } else if (cost <= 1) {
                        start = grid[i][j];
                        cost = 0;
                    } else {
                        G[start].add(new Edge(grid[i][j], cost));
                        G[grid[i][j]].add(new Edge(start, cost));
                        start = grid[i][j];
                        cost = 0;
                    }
                } else if (grid[i][j] == EMPTY) {
                    if (start != -1) {
                        cost++;
                    }
                }
                j++;
            }
        }


        for (int j = 0; j < W; j++) {
            int i = 0;
            int start = -1;
            int cost = 0;
            while (i < H) {
                if (grid[i][j] >= 2) {
                    if (start == -1) {
                        start = grid[i][j];
                    }  else if (cost <= 1) {
                        start = grid[i][j];
                        cost = 0;
                    } else {
                        G[start].add(new Edge(grid[i][j], cost));
                        G[grid[i][j]].add(new Edge(start, cost));
                        start = grid[i][j];
                        cost = 0;
                    }
                }  else if (grid[i][j] == EMPTY) {
                    if (start != -1) {
                        cost++;
                    }
                }
                i++;
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalCost = 0;
        int connectCnt = 0;
        boolean[] connected = new boolean[mapNum];
        for (Edge e : G[2]) {
            pq.offer(e);
        }
        connectCnt++;
        connected[2] = true;

        while (!pq.isEmpty() && connectCnt < V) {
            Edge e = pq.poll();

            if (!connected[e.to]) {
                connected[e.to] = true;
                connectCnt++;
                totalCost += e.c;
                for (Edge o : G[e.to]) {
                    if (!connected[o.to]) {
                        pq.offer(o);
                    }
                }
            }
        }

        if (connectCnt < V) {
            System.out.println(-1);
        } else {
            System.out.println(totalCost);
        }

    }


    private static void bfs(Queue<Point> q, int i, int j) {
        q.offer(new Point(i, j));
        grid[i][j] = mapNum;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (outOfBound(nr, nc))
                    continue;

                if (grid[nr][nc] == UNVISITED) {
                    grid[nr][nc] = mapNum;
                    q.offer(new Point(nr, nc));
                }
            }
        }
        mapNum++;
    }

    private static boolean outOfBound(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }

}