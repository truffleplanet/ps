import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		final int[] dr = { 1, -1, 0, 0 };
		final int[] dc = { 0, 0, 1, -1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]);
		int M = Integer.parseInt(tokens[1]);

		boolean[][] map = new boolean[N][M]; // padding

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				if (line.charAt(j) == '1') {
					map[i][j] = true;
				}
			}
		}

//		for (boolean[] maps : map) {
//			System.out.println(Arrays.toString(maps));
//		}

		Point start = new Point(0, 0, 1);
		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new ArrayDeque<>();
		visited[start.r][start.c] = true;
		queue.offer(start);
		int ans = -1;

		while (!queue.isEmpty()) {
			Point u = queue.poll();
//			System.out.println(u);
//			System.out.println(queue);
			int r = u.r;
			int c = u.c;
			if (r == N - 1 && c == M - 1) {
				ans = u.depth;
				break;
			}
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Point(nr, nc, u.depth + 1));
				}
			}
		}

		System.out.println(ans);
	}

}

class Point {
	int r;
	int c;
	int depth;

	public Point(int r, int c, int depth) {
		this.r = r;
		this.c = c;
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + ", depth=" + depth + "]";
	}

}
