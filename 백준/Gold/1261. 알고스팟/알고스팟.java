import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	final static int[] DR = { 1, -1, 0, 0 };
	final static int[] DC = { 0, 0, 1, -1 };

	static class State {
		int r, c;

		public State(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		char[][] grid = new char[H][W];
		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < W; j++) {
				grid[i][j] = s.charAt(j);
			}
		}

		int dist[][] = new int[H][W];
		for (int i = 0; i < H; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		Deque<State> dq = new ArrayDeque<>();
		dist[0][0] = 0;
		dq.add(new State(0, 0));

		while (!dq.isEmpty()) {
			State cur = dq.poll();
			int r = cur.r;
			int c = cur.c;

			if (r == H - 1 && c == W - 1) {
				System.out.println(dist[r][c]);
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + DR[d];
				int nc = c + DC[d];

				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;

				int w = grid[nr][nc] - '0';
				int nd = dist[r][c] + w;

				if (nd < dist[nr][nc]) {
					dist[nr][nc] = nd;
					if (w == 0)
						dq.addFirst(new State(nr, nc));
					else
						dq.addLast(new State(nr, nc));
				}

			}
		}

	}

}
