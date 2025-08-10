import java.util.Scanner;

public class Main {

	static int[] dr = { -1, -1, 1, 1, -1, 1, 0, 0 };
	static int[] dc = { -1, 1, -1, 1, 0, 0, -1, 1 };
	static int queenCnt;
	static int cnt;
	static int n;
	static boolean[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.close();

		map = new boolean[n][n];

		cnt = 0;
		backtrack(0);
		System.out.println(cnt);

	}

	public static void backtrack(int r) {
		if (r >= n) {
			cnt++;
			return;
		}

		for (int j = 0; j < n; j++) {
			boolean isOk = true;
			for (int k = 0; k < dr.length; k++) {
				int nr = r + dr[k];
				int nc = j + dc[k];
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
					continue;
				}

				int tr = nr;
				int tc = nc;
				while (tr >= 0 && tr < n && tc >= 0 && tc < n) {
					if (map[tr][tc]) {
						isOk = false;
						break;
					}
					tr += dr[k];
					tc += dc[k];
				}
				if (!isOk)
					break;
			}
			if (isOk) {
				map[r][j] = true;
				backtrack(r + 1);
				map[r][j] = false;
			}
		}
	}

}
