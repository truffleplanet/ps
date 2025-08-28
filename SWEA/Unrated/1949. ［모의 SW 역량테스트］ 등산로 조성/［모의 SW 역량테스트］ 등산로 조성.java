import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map;
	static int ans;
	static boolean[][] visited;

	static int[] DR = { 0, 0, 1, -1 };
	static int[] DC = { 1, -1, 0, 0 };

	static void dfs(int cnt, int r, int c) {
		boolean noMore = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + DR[i];
			int nc = c + DC[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (!visited[nr][nc] && map[nr][nc] < map[r][c]) {
				noMore = false;
				visited[nr][nc] = true;
				dfs(cnt + 1, nr, nc);
				visited[nr][nc] = false;
			}
		}
		if (noMore)
			ans = Math.max(ans, cnt);
	}

	static int getMax() {
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		return max;
	}

	static ArrayList<int[]> getStart() {
		ArrayList<int[]> startP = new ArrayList<>();
		int max = getMax();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == max) {
					startP.add(new int[] { i, j });
				}
			}
		}
		return startP;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			ans = 0;
			int K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ArrayList<int[]> startP = getStart();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int src = map[i][j];
					for (int t = 1; t <= K; t++) {
						map[i][j]--;
						for (int[] pos : startP) {
							if (pos[0] == i && pos[1] == j)
								continue;
							visited = new boolean[N][N];
							visited[pos[0]][pos[1]] = true;
							dfs(1, pos[0], pos[1]);
						}
					}
					map[i][j] = src;
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
