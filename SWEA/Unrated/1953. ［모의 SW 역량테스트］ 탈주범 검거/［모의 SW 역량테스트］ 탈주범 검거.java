import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int getKey(int r, int c, int dist) {
		return (dist << 20) | (r << 10) | c;
	}

	static int getR(int key) {
		return (key & ((1 << 20) - (1 << 10))) >> 10;
	}

	static int getC(int key) {
		return key & ((1 << 10) - 1);
	}

	static int getDist(int key) {
		return (key & ((1 << 32) - (1 << 20))) >> 20;
	}

	public static void main(String[] args) throws Exception {
		// 상 우 좌 하
		final int UP = 0;
		final int RIGHT = 1;
		final int LEFT = 2;
		final int DOWN = 3;
		final int[] DR = { -1, 0, 0, 1 };
		final int[] DC = { 0, 1, -1, 0 };

		// 배관별 탐색할 방향
		final int[][] DIRS = { {}, { UP, DOWN, LEFT, RIGHT }, { UP, DOWN }, { LEFT, RIGHT }, { UP, RIGHT },
				{ DOWN, RIGHT }, { LEFT, DOWN }, { LEFT, UP } };

		// dir, pipe 를 인덱스로 넣는다.
		final boolean[][] matching = { { false, true, true, false, false, true, true, false },
				{ false, true, false, true, false, false, true, true },
				{ false, true, false, true, true, true, false, false },
				{ false, true, true, false, true, false, false, true } };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[][] map = new int[H][W];
			int dist[][] = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			pq.offer(getKey(startR, startC, 0));
			dist[startR][startC] = 0;
			int cnt = 0;
			while (!pq.isEmpty()) {
				int key = pq.poll();
				int r = getR(key);
				int c = getC(key);
				int d = getDist(key);
				int curPipe = map[r][c];

				if (dist[r][c] != d)
					continue;

				if (dist[r][c] >= L)
					break;

				cnt++;

				for (int dir : DIRS[curPipe]) {
					int nr = r + DR[dir];
					int nc = c + DC[dir];
					int nd = d + 1;
					if (nr < 0 || nr >= H || nc < 0 || nc >= W)
						continue;

					if (!matching[dir][map[nr][nc]])
						continue;

					if (dist[nr][nc] > nd) {
						pq.offer(getKey(nr, nc, nd));
						dist[nr][nc] = nd;
					}
				}

			}

			sb.append('#').append(tc).append(' ').append(cnt).append('\n');

		}

		System.out.println(sb);
	}
}