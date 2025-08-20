import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * brute force 덩어리 찾기 
 * bfs로 하나의 덩어리를 찾을 수 있다.
 * 
 * 모든 노드를 집합에 넣어두고, 방문 시 삭제. 
 * 
 *  count++
 * 
 * 다 돌았는데, 집합에 노드가 남아있다면. 하나 꺼내서 다시 dfs. 
 * 
 * max count를 구하는 것 
 * 
 *
 * ## 매일 갉아먹기 최적화 
 * 갉아먹은 칸은 -1로 변환. 
 * 
 * 맛에 따라, 어느 칸인지 바로 찾을 수 있도록 
 * Map<Integer, ArrayList<Point>> 로 맛에 따른 칸 위치를 저장해두고 찾기 .
 * 
 */

public class Solution {

	static class Point {
		final int r;
		final int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int[][] map; // cheese
	static int N;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	static int bfs(HashSet<Integer> available) {
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();

		while (!available.isEmpty()) {
			for (int u : available) {
				q.add(u);
				available.remove(u);
				break;
			}
			cnt++;

			while (!q.isEmpty()) {
				int u = q.poll();
				int r = u / N;
				int c = u % N;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;

					if (map[nr][nc] == -1)
						continue;

					int idx = nr * N + nc;
					if (!available.contains(idx))
						continue;

					q.add(idx);
					available.remove(idx);

				}
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			Map<Integer, ArrayList<Point>> hash = new HashMap<>();
			HashSet<Integer> nodes = new HashSet<>(); // 갉아먹으면 지운다.

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int forHash = i * N;
				for (int j = 0; j < N; j++) {
					int v = Integer.parseInt(st.nextToken());
					map[i][j] = v;
					hash.putIfAbsent(v, new ArrayList<>());
					Point p = new Point(i, j);
					hash.get(v).add(p);
					nodes.add(forHash + j); // 꺼낼때는 / 해서 i, %해서 j
				}
			}

			int maxCC = 0;

			for (int day = 0; day <= 100; day++) {
				if (hash.containsKey(day)) {
					for (Point p : hash.get(day)) {
						int r = p.r;
						int c = p.c;
						map[r][c] = -1;
						nodes.remove(r * N + c);
					}
				}
				HashSet<Integer> available = (HashSet<Integer>) nodes.clone();
				int cnt = bfs(available);
				maxCC = Math.max(cnt, maxCC);

			}

			sb.append("#").append(tc).append(" ").append(maxCC).append("\n");

		}

		System.out.println(sb);
	}
}
