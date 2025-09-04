import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		final int[] DR = { 1, 0, -1, 0 };
		final int[] DC = { 0, 1, 0, -1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int startR = 0;
		int startC = 0;

		int endR = N - 1;
		int endC = M - 1;

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][M][2];

		q.offer(new int[] { startR, startC, 1, 0 }); // r, c, move, state
		visited[startR][startC][0] = true;

		while (!q.isEmpty()) {
			int[] u = q.poll();
			int r = u[0];
			int c = u[1];
			int m = u[2];
			int state = u[3];

			if (r == endR && c == endC) {
				System.out.println(m);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + DR[i];
				int nc = c + DC[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (map[nr][nc] == '1' && state == 0 && !visited[nr][nc][1]) {
					visited[nr][nc][1] = true;
					q.offer(new int[] { nr, nc, m + 1, 1 });
					continue;
				}

				if (map[nr][nc] == '0' && !visited[nr][nc][state]) {
					q.offer(new int[] { nr, nc, m + 1, state });
					visited[nr][nc][state] = true;
				}

			}

		}

		System.out.println(-1);

	}
}
