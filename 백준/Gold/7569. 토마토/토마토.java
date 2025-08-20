import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int z;
		int y;
		int x;
		int depth;

		public Node(int z, int y, int x, int depth) {
			super();
			this.z = z;
			this.y = y;
			this.x = x;
			this.depth = depth;
		}

	}

	public static void main(String[] args) throws IOException {
		int[] dz = { 1, -1, 0, 0, 0, 0 };
		int[] dy = { 0, 0, 1, -1, 0, 0 };
		int[] dx = { 0, 0, 0, 0, 1, -1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int X = Integer.parseInt(st.nextToken()); // 가로
		int Y = Integer.parseInt(st.nextToken()); // 세로
		int Z = Integer.parseInt(st.nextToken()); // 상자 쌓기

		int[][][] map = new int[Z][Y][X];

		Queue<Node> q = new ArrayDeque<>();

		int targetCnt = 0;

		for (int z = 0; z < Z; z++) {
			for (int y = 0; y < Y; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < X; x++) {
					int v = Integer.parseInt(st.nextToken());
					map[z][y][x] = v;

					if (v == 0)
						targetCnt++;

					if (v == 1)
						q.offer(new Node(z, y, x, 0));
				}
			}
		}

		int ans = 0;

		while (!q.isEmpty()) {
			Node u = q.poll();
			int z = u.z;
			int y = u.y;
			int x = u.x;
			int depth = u.depth;
			ans = depth; // 매번 최대로 갱신하기.

			for (int d = 0; d < 6; d++) {
				int nz = z + dz[d];
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (nz < 0 || nz >= Z || ny < 0 || ny >= Y || nx < 0 || nx >= X)
					continue;

				if (map[nz][ny][nx] == 0) {
					targetCnt--;
					map[nz][ny][nx] = 1;
					q.add(new Node(nz, ny, nx, depth + 1));
				}
			}

		}

		if (targetCnt != 0) {
			ans = -1;
		}

		System.out.println(ans);

	}

}
