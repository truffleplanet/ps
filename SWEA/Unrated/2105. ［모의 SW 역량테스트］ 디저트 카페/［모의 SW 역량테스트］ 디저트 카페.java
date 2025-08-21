import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[][] map;
	static boolean[] isSelected;
	static int N;
	// 우하, 좌하, 좌상, 우상
	static int[] DR = { 1, 1, -1, -1 };
	static int[] DC = { 1, -1, -1, 1 };

	static int startR;
	static int startC;
	static int ans;

	public static void dfs(int r, int c, int d, int cnt) {
		for (int nd = d; nd < 4; nd++) {
			int nr = r + DR[nd];
			int nc = c + DC[nd];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (nr == startR && nc == startC && nd == 3 && cnt >= 4) {
				ans = Math.max(ans, cnt);
				return;
			}

			if (isSelected[map[nr][nc]])
				continue;

			isSelected[map[nr][nc]] = true;
			dfs(nr, nc, nd, cnt + 1);
			isSelected[map[nr][nc]] = false;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ans = -1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					isSelected = new boolean[101];
					startR = i;
					startC = j;

					isSelected[map[i][j]] = true;
					dfs(startR, startC, 0, 1);
					isSelected[map[i][j]] = false;
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}
}
