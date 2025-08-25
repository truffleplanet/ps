import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	final static int[] dr = { 0, 0, 1, 1, 1, -1, -1, -1 };
	final static int[] dc = { 1, -1, 1, -1, 0, 1, -1, 0 };
	static int N;
	static char[][] map = new char[300][300];
	static boolean[][] visited = new boolean[300][300];

	// utilities
	public static char countMine(int r, int c) {
		if (map[r][c] == '*')
			return '*';

		char cnt = '0';
		for (int t = 0; t < 8; t++) {
			int nr = r + dr[t];
			int nc = c + dc[t];

			if (!boundCheck(nr, nc))
				continue;

			if (map[nr][nc] == '*')
				cnt++;
		}
		return cnt;
	}

	public static boolean boundCheck(int r, int c) {
		return !(r < 0 || r >= N || c < 0 || c >= N);
	}

	// core
	public static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] u = q.poll();
			r = u[0];
			c = u[1];

			for (int t = 0; t < 8; t++) {
				int nr = r + dr[t];
				int nc = c + dc[t];

				if (!boundCheck(nr, nc))
					continue;

				if (visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				if (map[nr][nc] == '0') {
					q.offer(new int[] { nr, nc });
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 입력, 리셋
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}

			// 숫자 기록하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = countMine(i, j);
				}
			}

			int ans = 0;

			// bfs 하면서 방문 체크.
			// 0에서 첫 bfs 해나간다면 기록하기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '*') {
						visited[i][j] = true;
						continue;
					}

					if (visited[i][j])
						continue;

					if (map[i][j] == '0') {
						bfs(i, j);
						ans++;
					}
				}
			}

//			 방문하지 못한 곳 더하기.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j])
						ans++;
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		} // 모든 test case 종료

		System.out.println(sb);
	}

}
