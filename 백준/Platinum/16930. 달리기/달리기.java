import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] dp;
	static char[][] map;
	static int K;
	static int N;
	static int M;

	final static int[] DR = { 0, 0, 1, -1 };
	final static int[] DC = { 1, -1, 0, 0 };

	static void bfs(int startR, int startC) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { startR, startC });

		while (!q.isEmpty()) {
			int[] u = q.poll();
			int r = u[0];
			int c = u[1];
			int cur = dp[r][c];

			for (int d = 0; d < 4; d++) {
				for (int t = 1; t <= K; t++) {
					int nr = r + (t * DR[d]);
					int nc = c + (t * DC[d]);

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						break;

					if (map[nr][nc] == '#')
						break;

					if (dp[nr][nc] < cur + 1)
						break;

					if (dp[nr][nc] == cur + 1)
						continue;

					if (dp[nr][nc] > cur + 1) {
						dp[nr][nc] = cur + 1;
						q.offer(new int[] { nr, nc });
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N][M];
		map = new char[N][M];

		// input
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// init dp
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// input start pos, end pos
		st = new StringTokenizer(br.readLine());
		int startR = Integer.parseInt(st.nextToken());
		int startC = Integer.parseInt(st.nextToken());
		int endR = Integer.parseInt(st.nextToken());
		int endC = Integer.parseInt(st.nextToken());

		// exception. start or end == obstacle
		if (map[startR - 1][startC - 1] == '#' || map[endR - 1][endC - 1] == '#') {
			System.out.println(-1);
			return;
		}

		// start
		dp[startR - 1][startC - 1] = 0;
		bfs(startR - 1, startC - 1);

		// exception. cant reach
		if (dp[endR - 1][endC - 1] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dp[endR - 1][endC - 1]);
		}
	}

}
