import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

	static int[] DR = { -1, 0, 1, 0 };
	static int[] DC = { 0, 1, 0, -1 };

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		char[][] grid = new char[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			grid[i] = br.readLine().toCharArray();
		}

		Queue<Node> q = new ArrayDeque<>();
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (visited[i][j])
					continue;

				visited[i][j] = true;
				q.offer(new Node(i, j));
				cnt++;
				while (!q.isEmpty()) {
					Node nod = q.poll();
					int r = nod.r;
					int c = nod.c;

					for (int d = 0; d < 4; d++) {
						int nr = r + DR[d];
						int nc = c + DC[d];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;

						if (visited[nr][nc])
							continue;

						if (grid[r][c] == grid[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new Node(nr, nc));
						}

					}
				}
			}
		}

		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);

		int cnt2 = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (visited[i][j])
					continue;

				if (grid[i][j] == 'R')
					grid[i][j] = 'G';

				visited[i][j] = true;
				q.offer(new Node(i, j));
				cnt2++;
				while (!q.isEmpty()) {
					Node nod = q.poll();
					int r = nod.r;
					int c = nod.c;

					for (int d = 0; d < 4; d++) {
						int nr = r + DR[d];
						int nc = c + DC[d];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;

						if (visited[nr][nc])
							continue;

						if (grid[nr][nc] == 'R')
							grid[nr][nc] = 'G';

						if (grid[r][c] == grid[nr][nc]) {
							visited[nr][nc] = true;
							q.offer(new Node(nr, nc));
						}

					}
				}
			}
		}

		sb.append(cnt).append(' ').append(cnt2);
		System.out.println(sb);
	}

}
