import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

	static class Node implements Comparable<Node> {
		int r;
		int c;
		int w;

		public Node(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static int[] DR = { 0, 0, -1, 1 };
	static int[] DC = { 1, -1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}

			int endR = N - 1;
			int endC = N - 1;

			PriorityQueue<Node> pq = new PriorityQueue<>();
			Node start = new Node(0, 0, 0);
			pq.offer(start);
			dist[start.r][start.c] = 0;

			while (!pq.isEmpty()) {
				Node u = pq.poll();

				if (dist[u.r][u.c] != u.w)
					continue;

				if (u.r == endR && u.c == endC)
					break;

				for (int i = 0; i < 4; i++) {
					int nr = u.r + DR[i];
					int nc = u.c + DC[i];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;

					int nd = map[nr][nc] + u.w;
					if (dist[nr][nc] > nd) {
						dist[nr][nc] = nd;
						pq.offer(new Node(nr, nc, nd));
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(dist[endR][endC]).append("\n");

		}
		System.out.println(sb);

	}

}
