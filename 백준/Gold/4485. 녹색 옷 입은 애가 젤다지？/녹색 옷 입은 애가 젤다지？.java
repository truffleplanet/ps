import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 정의:
 * (0,0)에서 (N-1, N-1)로 최소 비용으로 이동할 때의 비용을 구하여라
 * 
 * 제약조건:
 * 이동은 상 하 좌 우 
 * 칸 별 정수는 0~9(그치만 char로 아낄 이유는 없는 지도 크기)
 * 지도의 한 변의 길이 N은 최대 125*125
 * 
 * solution:
 * 알려진 덤텅이 bfs
 * 최소 비용 맵을 따로 하나 만들어두고, 최소비용을 업데이트 할 수 있으면 이동, 아니면 가만히 있기.
 */

public class Main {

	static class Node {
		int r;
		int c;

		private Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int[] DR = { 0, 0, -1, 1 };
		final int[] DC = { 1, -1, 0, 0 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String prob = "Problem";

		int tc = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}

			int[][] map = new int[N][N];
			int[][] sumMap = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// bfs 준비
			for (int i = 0; i < N; i++) {
				Arrays.fill(sumMap[i], Integer.MAX_VALUE);
			}
			sumMap[0][0] = map[0][0];

			// bfs
			Queue<Node> q = new ArrayDeque<>();
			q.offer(new Node(0, 0));
			while (!q.isEmpty()) {
				Node u = q.poll();

				for (int k = 0; k < 4; k++) {
					int nr = u.r + DR[k];
					int nc = u.c + DC[k];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;

					int candidate = sumMap[u.r][u.c] + map[nr][nc];
					if (sumMap[nr][nc] > candidate) {
						sumMap[nr][nc] = candidate;
						q.offer(new Node(nr, nc));
					}
				}
			} // end of bfs

			sb.append(prob).append(" ").append(tc++).append(":").append(" ").append(sumMap[N - 1][N - 1]).append("\n");
		}

		System.out.println(sb);
	}

}
