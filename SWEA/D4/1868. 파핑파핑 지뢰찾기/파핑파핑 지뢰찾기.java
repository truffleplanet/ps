import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int[] dr = { 0, 0, 1, 1, 1, -1, -1, -1 };
	static int[] dc = { 1, -1, 1, -1, 0, 1, -1, 0 };

	// find
	private static int find(int[] parents, int x) {
		if (parents[x] == x)
			return x;

		parents[x] = find(parents, parents[x]);
		return parents[x];
	}

	public static int union(int[] parents, int x, int y) {
		int x_root = find(parents, x);
		int y_root = find(parents, y);

		if (x_root == y_root)
			return 0;

		parents[x_root] = y_root;
		return 1;
	}

	public static char countMine(char[][] map, int N, int r, int c) {
		char cnt = '0';
		for (int t = 0; t < 8; t++) {
			int nr = r + dr[t];
			int nc = c + dc[t];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (map[nr][nc] == '*')
				cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		char[][] map = new char[300][300];
		int[] parents = new int[300 * 300];
		boolean[] seenRoot = new boolean[300 * 300];

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int nodes = N * N;

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// parents 배열 초기화.
			// 노드는 0 부터 N * N -1까지니깐. 이 범위면 맞음.

			for (int i = 0; i < nodes; i++) {
				parents[i] = i;
				seenRoot[i] = false;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (map[i][j] == '*') {
						continue;
					}

					map[i][j] = countMine(map, N, i, j);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '0') {
						int x = (i * N) + j;
						for (int t = 0; t < 8; t++) {

							int nr = i + dr[t];
							int nc = j + dc[t];
							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;

							if (map[nr][nc] == '0') {
								int y = (nr * N) + nc;
								union(parents, x, y);
							}
						}
					}
				}
			}

			int zeroComponents = 0;
			int isolatedComponents = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '0') {
						int root = find(parents, i * N + j);
						if (!seenRoot[root]) {
							seenRoot[root] = true;
							zeroComponents++;
						}
					} else if (map[i][j] != '*') {
						boolean isolated = true;
						for (int t = 0; t < 8; t++) {
							int nr = i + dr[t];
							int nc = j + dc[t];

							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;

							if (map[nr][nc] == '0') {
								isolated = false;
								break;
							}
						}
						if (isolated)
							isolatedComponents++;
					}
				}
			}

			int ans = zeroComponents + isolatedComponents;
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

}
