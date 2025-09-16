import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {

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
		final int[] DR = { -1, 0, 1, 0 };
		final int[] DC = { 0, 1, 0, -1 };
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		boolean[][] isValid = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < N; j++) {
				isValid[i][j] = (row.charAt(j) == '1') ? true : false;
			}
		}

		List<Integer> flats = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isValid[i][j]) {
					Queue<Node> q = new ArrayDeque<>();
					isValid[i][j] = false;
					q.offer(new Node(i, j));
					int cnt = 1;

					while (!q.isEmpty()) {
						Node u = q.poll();
						int r = u.r;
						int c = u.c;

						for (int d = 0; d < 4; d++) {
							int nr = r + DR[d];
							int nc = c + DC[d];

							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;

							if (isValid[nr][nc]) {
								isValid[nr][nc] = false;
								cnt++;
								q.offer(new Node(nr, nc));
							}
						}
					} // bfs 종료
					flats.add(cnt);
				}
			}
		} // 탐색 종료

		// 최악이 N^2 * 2

		Collections.sort(flats);
		sb.append(flats.size()).append('\n');
		for (int n : flats) {
			sb.append(n).append('\n');
		}

		System.out.println(sb);

	}

}
