import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] DR = { 0, 0, 1, -1 };
	static int[] DC = { 1, -1, 0, 0 };
	static int H, W;
	static int ans;

	static void pung(int[][] map, int r, int c) {
		int d = map[r][c];
		map[r][c] = 0;

		if (d <= 1) {
			return;
		}

		for (int t = 1; t < d; t++) {
			for (int k = 0; k < 4; k++) {
				int nr = r + (t * DR[k]);
				int nc = c + (t * DC[k]);

				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;

				pung(map, nr, nc);
			}
		}
	}

	static void gravity(int[][] map) {
		for (int c = 0; c < W; c++) {
			for (int r = H - 2; r >= 0; r--) {
				int nr = r + 1;
				while (nr < H && map[nr][c] == 0) {
					map[nr][c] = map[nr - 1][c];
					map[nr - 1][c] = 0;
					nr++;
				}
			}
		}
	}

	static int count(int[][] map) {
		int cnt = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] > 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	static void machine(int[][] map, int N) {
		if (N == 0) {
			ans = Math.min(ans, count(map));
			return;
		}
		for (int c = 0; c < W; c++) {
			int h = 0;
			for (int r = 0; r < H; r++) {
				if (map[r][c] != 0) {
					h = r;
					break;
				}
			}

			int[][] copy = deepcopy(map);
			pung(copy, h, c);
			gravity(copy);
			machine(copy, N - 1);
		}
	}

	private static int[][] deepcopy(int[][] src) {
		int[][] out = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				out[i][j] = src[i][j];
			}
		}
		return out;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			machine(map, N);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

}
