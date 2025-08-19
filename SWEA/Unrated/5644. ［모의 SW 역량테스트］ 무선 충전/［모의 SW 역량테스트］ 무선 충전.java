import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	// 이동하지 않음, 상, 우, 하, 좌.. 상이 - 하가 +인 좌표계임..
	static final int[] dx = { 0, 0, 1, 0, -1 };
	static final int[] dy = { 0, -1, 0, 1, 0 };

	static class BC {
		int id;
		int x;
		int y;
		int c;
		int p;

		public BC(int id, int x, int y, int coverage, int performance) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.c = coverage;
			this.p = performance;
		}

		public boolean covers(int x, int y) {
			return (Math.abs(x - this.x) + Math.abs(y - this.y)) <= this.c;
		}

	}

	// 여기가 메인
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 총 이동 횟수
			int A = Integer.parseInt(st.nextToken()); // BC의 총 갯수

			// 다 움직이고 마지막에 한번 더 세야하므로,M+1로 패딩,
			int[] userA_Move = new int[M + 1];
			int[] userB_Move = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				userA_Move[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				userB_Move[i] = Integer.parseInt(st.nextToken());

			BC[] bcs = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcs[i] = new BC(i, x, y, c, p);
			}

			int ax = 1;
			int ay = 1;
			int bx = 10;
			int by = 10;
			int ans = 0;
			List<BC> coverA = new ArrayList<>();
			List<BC> coverB = new ArrayList<>();
			for (int i = 0; i <= M; i++) { // 계산 -> 이동 -> 다음 iteration

				coverA.clear();
				coverB.clear();

				// 가능한 BC 찾기, 저장
				for (BC bc : bcs) {
					if (bc.covers(ax, ay))
						coverA.add(bc);
					if (bc.covers(bx, by))
						coverB.add(bc);
				}

				Collections.sort(coverA, (o1, o2) -> (o2.p - o1.p));
				Collections.sort(coverB, (o1, o2) -> (o2.p - o1.p));

				int bestA = 0;
				int bestB = 0;
				if (coverA.isEmpty() || coverB.isEmpty()) {
					if (!coverA.isEmpty()) {
						bestA = coverA.get(0).p;
					} else if (!coverB.isEmpty()) {
						bestB = coverB.get(0).p;
					}
				} else {
					if (coverA.get(0).id != coverB.get(0).id) {
						bestA = coverA.get(0).p;
						bestB = coverB.get(0).p;
					} else {
						bestA = coverA.get(0).p;
						bestB = coverB.get(0).p;
						BC a = null, b = null;
						if (coverA.size() > 1) {
							a = coverA.get(1);
						}
						if (coverB.size() > 1) {
							b = coverB.get(1);
						}

						if (a == null || b == null) {
							if (a != null) {
								bestA = a.p;
							} else if (b != null) {
								bestB = b.p;
							} else {
								bestB = 0;
							}
						} else if (a.id == b.id) {
							bestB = b.p;
						} else {
							if (a.p > b.p) {
								bestA = a.p;
							} else {
								bestB = b.p;
							}
						}
					}
				}

				ans += bestA + bestB;
				// 이동
				ax += dx[userA_Move[i]];
				ay += dy[userA_Move[i]];
				bx += dx[userB_Move[i]];
				by += dy[userB_Move[i]];

			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

}
