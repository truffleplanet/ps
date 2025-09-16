import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 상 우 하 좌 시계 방향
		final int[] DR = { -1, 0, 1, 0 };
		final int[] DC = { 0, 1, 0, -1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');
			int N = Integer.parseInt(br.readLine());
			char[][] grid = new char[N][N];
			int sr = 0;
			int sc = 0;
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					int ch = line[j];
					if (ch == 'X') {
						sr = i;
						sc = j;
					}
					grid[i][j] = line[j];
				}
			}

			// 명령 입력

			int Q = Integer.parseInt(br.readLine());
			for (int $ = 0; $ < Q; $++) {
				st = new StringTokenizer(br.readLine());
				int L = Integer.parseInt(st.nextToken());
				char cmd[] = st.nextToken().toCharArray();

				int dir = 0;
				int r = sr;
				int c = sc;

				for (int i = 0; i < L; i++) {
					switch (cmd[i]) {
					case 'A':
						int nr = r + DR[dir];
						int nc = c + DC[dir];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;

						if (grid[nr][nc] == 'T')
							continue;

						r = nr;
						c = nc;
						break;
					case 'L':
						dir = (dir + 3) % 4;
						break;
					case 'R':
						dir = (dir + 1) % 4;
						break;
					}
				}
				int ans = 0;
				if (grid[r][c] == 'Y') {
					ans = 1;
				}
				sb.append(ans).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
