import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map;
	static int[][] dp;
	static final int[] dr = { 1, -1, 0, 0 };
	static final int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int bestLen = 0;
			int bestStart = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int len = dfs(i, j);
					int startVal = map[i][j];
					if (len > bestLen || (len == bestLen && startVal < bestStart)) {
						bestLen = len;
						bestStart = startVal;
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(bestStart).append(' ').append(bestLen).append('\n');
		}
		System.out.print(sb);
	}

	static int dfs(int r, int c) {
		if (dp[r][c] != 0)
			return dp[r][c];

		int cur = map[r][c];
		int best = 1;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d], nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (map[nr][nc] == cur + 1) {
				best = 1 + dfs(nr, nc);
				break;
			}
		}
		dp[r][c] = best;
		return best;
	}
}