import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int C_BITS = 10;
	static final int R_BITS = 22;
	static final int C_MASK = (1 << C_BITS) - 1;
	static final int R_MASK = (1 << R_BITS) - 1;

	static final int[] DR = { 1, -1, 0, 0 };
	static final int[] DC = { 0, 0, 1, -1 };

	static int getKey(int r, int c) {
		return ((r & R_MASK) << C_BITS) | (c & C_MASK);
	}

	static int getR(int key) {
		return (key >>> C_BITS) & R_MASK;
	}

	static int getC(int key) {
		return (key & C_MASK);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		char[][] grid = new char[H][W];
		int sr = 0, sc = 0;

		for (int i = 0; i < H; i++) {
			String s = br.readLine().trim();
			for (int j = 0; j < W; j++) {
				char ch = s.charAt(j);
				if (ch == 'I') {
					sr = i;
					sc = j;
					ch = 'O';
				}
				grid[i][j] = ch;
			}
		}

		boolean[][] visited = new boolean[H][W];
		Queue<Integer> q = new ArrayDeque<>();
		visited[sr][sc] = true;
		q.add(getKey(sr, sc));

		int cnt = 0;
		while (!q.isEmpty()) {
			int key = q.remove();
			int r = getR(key);
			int c = getC(key);

			for (int d = 0; d < 4; d++) {
				int nr = r + DR[d];
				int nc = c + DC[d];

				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;

				char ch = grid[nr][nc];

				if (ch == 'X' || visited[nr][nc])
					continue;

				if (ch == 'P') {
					cnt++;
				}

				visited[nr][nc] = true;
				q.offer(getKey(nr, nc));
			}
		}

		if (cnt == 0) {
			System.out.println("TT");
		} else {
			System.out.println(cnt);
		}

	}

}
