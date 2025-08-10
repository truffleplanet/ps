import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());// columns
		int N = Integer.parseInt(st.nextToken());// rows

		int[][] map = new int[N][M];
		int[][] dist = new int[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		int zeros = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int state = Integer.parseInt(st.nextToken());
				map[i][j] = state;
				if (state == 1) {
					q.offer(new int[] { i, j });
					dist[i][j] = 1;
				}
				if (state == 0) {
					zeros++;
				}
			}
		}

		int r = -1, c = -1;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			r = u[0];
			c = u[1];

			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;
				if (map[nr][nc] != 0)
					continue;
				if (dist[nr][nc] != 0)
					continue;
				dist[nr][nc] = dist[r][c] + 1;
				map[nr][nc] = 1;
				zeros--;
				q.offer(new int[] { nr, nc });
			}
		}

		if (zeros == 0) {
			System.out.println(dist[r][c] - 1);
		} else {
			System.out.println(-1);
		}

	}

}
