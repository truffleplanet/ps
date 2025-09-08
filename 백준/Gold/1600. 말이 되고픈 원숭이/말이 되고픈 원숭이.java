/*
 * K번만 대각 이동할 수 있다는 상태를 가지고 이동한다.
 * 
 * 다익스트라 
 * 
 * dist[r][c][k사용량] 
 * 
 * 1. dist[r][c][k]와 비교 
 * 
 * 2. dist[r][c][0..k-1] 중 
 * 	dist[r][c][k]보다 작은 것이 있다면 x.
 * 
 * 3. 
 * 그럼 아예 .. dist[r][c][k]를 기록할 때, 
 * 더 큰 k에다가, 내 최솟값을 박아버리자. 
 * 
 * 
 * 4, 그거냐? 아니면 큰거에서 작은거 보면서,...
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int DR[] = { 1, -1, 0, 0 };
	static final int DC[] = { 0, 0, 1, -1 };

	static final int KDR[] = { -2, -2, -1, +1, +2, +2, +1, -1 };
	static final int KDC[] = { -1, +1, +2, +2, -1, +1, -2, -2 };

	static class HeapNode implements Comparable<HeapNode> {
		int r;
		int c;
		int k;
		int m;

		public HeapNode(int r, int c, int k, int d) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
			this.m = d;
		}

		@Override
		public int compareTo(HeapNode o) {
			int v1 = Integer.compare(this.m, o.m);
			if (v1 != 0)
				return v1;
			return Integer.compare(this.k, o.k);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		boolean[][] isObstacle = new boolean[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				char x = st.nextToken().charAt(0);
				if (x == '1')
					isObstacle[i][j] = true;
			}
		}

		int[][][] dist = new int[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		}

		PriorityQueue<HeapNode> pq = new PriorityQueue<>();
		pq.offer(new HeapNode(0, 0, 0, 0));
		dist[0][0][0] = 0;

		while (!pq.isEmpty()) {
			HeapNode node = pq.poll();
			int r = node.r;
			int c = node.c;
			int k = node.k;
			int m = node.m;

			if (dist[r][c][k] != m)
				continue;

			if (r == H - 1 && c == W - 1)
				break;

			for (int i = 0; i < 4; i++) {
				int nr = r + DR[i];
				int nc = c + DC[i];
				int nm = m + 1;

				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;

				if (isObstacle[nr][nc])
					continue;

				if (dist[nr][nc][k] > nm) {
//					for (int t = k; t <= K; t++) {
//						if (dist[nr][nc][t] > nm) {
//							dist[nr][nc][t] = nm;
//						} else {
//							break;
//						}
//					}
					dist[nr][nc][k] = nm;
					pq.offer(new HeapNode(nr, nc, k, nm));

				}
			}

			if (k < K) {
				for (int i = 0; i < 8; i++) {
					int nr = r + KDR[i];
					int nc = c + KDC[i];
					int nk = k + 1;
					int nm = m + 1;

					if (nr < 0 || nr >= H || nc < 0 || nc >= W)
						continue;

					if (isObstacle[nr][nc])
						continue;

					if (dist[nr][nc][nk] > nm) {
//						for (int t = nk; t <= K; t++) {
//							if (dist[nr][nc][t] > nm) {
//								dist[nr][nc][t] = nm;
//							} else {
//								break;
//							}
//						}
						dist[nr][nc][nk] = nm;
						pq.offer(new HeapNode(nr, nc, nk, nm));

					}
				}
			}
		}

		int ans = Integer.MAX_VALUE;

		for (int i = 0; i <= K; i++) {
			ans = Math.min(ans, dist[H - 1][W - 1][i]);
//			System.out.println(dist[H - 1][W - 1][i]);
		}

		if (ans == Integer.MAX_VALUE)
			ans = -1;

		System.out.println(ans);

	}
}
