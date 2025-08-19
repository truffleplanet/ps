import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int W;
	static int H;
	static String[][] map;

	static class Tank {
		// up right left down
		static int[] dr = { -1, 0, 0, 1 };
		static int[] dc = { 0, 1, -1, 0 };
		static String[] transTable = { "^", ">", "<", "v" };
		int dir;
		int r;
		int c;

		public Tank(int dir, int r, int c) {
			super();
			this.dir = dir;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return transTable[dir];
		}

		public void action(char command) {
			if (command == 'S')
				shot();
			else
				move(command);

		}

		private void shot() {
			int nr = this.r + dr[dir];
			int nc = this.c + dc[dir];
			while (checkBound(nr, nc)) {
				String stuff = map[nr][nc];
				if (stuff.equals("*")) {
					map[nr][nc] = ".";
					return;
				}
				if (stuff.equals("#"))
					return;
				nr += dr[dir];
				nc += dc[dir];
			}
		}

		private void move(char command) {
			switch (command) {
			case 'U':
				this.dir = 0;
				break;
			case 'R':
				this.dir = 1;
				break;
			case 'L':
				this.dir = 2;
				break;
			default:
				this.dir = 3;
				break;
			}
			int nr = this.r + dr[dir];
			int nc = this.c + dc[dir];
			if (checkBound(nr, nc) && map[nr][nc].equals(".")) {
				map[r][c] = ".";
				r = nr;
				c = nc;
			}
			map[r][c] = this.toString();
		}

		private boolean checkBound(int r, int c) {
			return r >= 0 && r < H && c >= 0 && c < W;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new String[H][W];

			int dir = -1;
			int r = -1;
			int c = -1;

			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = String.valueOf(line.charAt(j));
					if (map[i][j].equals("^")) {
						dir = 0;
						r = i;
						c = j;
					}
					if (map[i][j].equals(">")) {
						dir = 1;
						r = i;
						c = j;
					}
					if (map[i][j].equals("<")) {
						dir = 2;
						r = i;
						c = j;
					}
					if (map[i][j].equals("v")) {
						dir = 3;
						r = i;
						c = j;
					}
				}
			}

			Tank tank = new Tank(dir, r, c);

			int N = Integer.parseInt(br.readLine());
			char[] commands = br.readLine().toCharArray();
			for (char command : commands) {
				tank.action(command);
			}

			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);

	}
}
