
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static final int[] dr = { 1, -1, 0, 0 };
	static final int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);

		boolean[][] map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == '1') {
					map[i][j] = true;
				}
			}
		}

		Queue<int[]> queue = new ArrayDeque<>();
		int[][] dist = new int[N][M];
		dist[0][0] = 1;
		queue.offer(new int[] { 0, 0 });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			if (r == N - 1 && c == M - 1) {
				break;
			}

			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (!map[nr][nc])
					continue;
				if (dist[nr][nc] != 0)
					continue;
				dist[nr][nc] = dist[r][c] + 1;
				queue.offer(new int[] { nr, nc });
			}
		}

		System.out.println(dist[N - 1][M - 1]);

	}
}
