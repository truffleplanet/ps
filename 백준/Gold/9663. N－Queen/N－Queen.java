import java.util.Scanner;

public class Main {
	static boolean[] col;
	static boolean[][] map;
	static int n;
	static int count = 0;
	static int[] dr = { 1, 1, -1, -1 };
	static int[] dc = { 1, -1, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.close();

		col = new boolean[n];
		map = new boolean[n][n];

		backtrack(0);
		System.out.println(count);

	}

	public static void backtrack(int r) {
		if (r == n) {
			count++;
		}

		for (int c = 0; c < n; c++) {
			boolean isOk = true;

			if (col[c])
				continue;

			for (int k = 0; k < 4; k++) {
				int nr = r;
				int nc = c;
				while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
					if (map[nr][nc]) {
						isOk = false;
						break;
					}
					nr += dr[k];
					nc += dc[k];
				}
				if (!isOk)
					break;
			}

			if (isOk) {
				map[r][c] = col[c] = true;
				backtrack(r + 1);
				map[r][c] = col[c] = false;
			}
		}
	}
}