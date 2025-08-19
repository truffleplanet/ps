import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		// 이동하지 않음, 상, 우, 하, 좌.. 상이 - 하가 +인 좌표계임..
		int[] dx = { 0, 0, 1, 0, -1 };
		int[] dy = { 0, -1, 0, 1, 0 };

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 총 이동 횟수
			int A = Integer.parseInt(st.nextToken()); // BC의 총 갯수

			int[] userA_Move = new int[M + 1];
			int[] userB_Move = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				userA_Move[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				userB_Move[i] = Integer.parseInt(st.nextToken());
			}

			BC[] bcs = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcs[i] = new BC(i, x, y, c, p);
			}

			int[] userA_Pos = { 1, 1 };
			int[] userB_Pos = { 10, 10 };
			// 마지막에 움직이지 않는 것 하나 만들어둡니다.
			userA_Move[M] = 0;
			userB_Move[M] = 0;

			int ans = 0;
			List<BC> aBC = new ArrayList<>();
			List<BC> bBC = new ArrayList<>();
			for (int i = 0; i <= M; i++) { // 계산 -> 이동 -> 다음 iteration

				aBC.clear();
				bBC.clear();

				// 가능한 BC 찾기, 저장
				for (BC bc : bcs) {
					if (bc.isCover(userA_Pos[0], userA_Pos[1]))
						aBC.add(bc);
					if (bc.isCover(userB_Pos[0], userB_Pos[1]))
						bBC.add(bc);
				}

				Collections.sort(aBC, (o1, o2) -> (o2.performance - o1.performance));
				Collections.sort(bBC, (o1, o2) -> (o2.performance - o1.performance));

				int bestA = 0;
				int bestB = 0;
				if (aBC.isEmpty() || bBC.isEmpty()) {
					if (!aBC.isEmpty()) {
						bestA = aBC.get(0).performance;
					} else if (!bBC.isEmpty()) {
						bestB += bBC.get(0).performance;
					}
				} else {
					if (aBC.get(0).id != bBC.get(0).id) {
						bestA = aBC.get(0).performance;
						bestB = bBC.get(0).performance;
					} else {
						bestA = aBC.get(0).performance;
						bestB = bBC.get(0).performance;
						BC a = null, b = null;
						if (aBC.size() > 1) {
							a = aBC.get(1);
						}
						if (bBC.size() > 1) {
							b = bBC.get(1);
						}

						if (a == null || b == null) {
							if (a != null) {
								bestA = a.performance;
							} else if (b != null) {
								bestB = b.performance;
							} else {
								bestB = 0;
							}
						} else if (a.id == b.id) {
							bestB = b.performance;
						} else {
							if (a.performance > b.performance) {
								bestA = a.performance;
							} else {
								bestB = b.performance;
							}
						}
					}
				}

				ans += bestA + bestB;

//					if (bcs[k].isCover(userA_Pos[0], userA_Pos[1])) {
				// 이런식으로 하면.는데, 최대가 실제로는 다를 수도 있는데, 같은 선택지 뽑게 되기도 한다.
				// 그래서 그냥 가능한 충전소 전부 저장하고 뽑아보는게 맞겠다.
//						if (bcs[k].performance >= aBc[0][1]) {
//							aBc[1][0] = aBc[0][0]; // 2등이 1등 정보를 받고
//							aBc[1][1] = aBc[0][1];
//							aBc[0][1] = bcs[k].performance; // 1등 정보 갱신
//							aBc[0][0] = k;
//						} else if (bcs[k].performance >= aBc[1][1]) {
//							aBc[1][1] = bcs[k].performance;
//							aBc[1][0] = k;
//						}
//					}
//					if (bcs[k].isCover(userB_Pos[0], userB_Pos[1])) {
//						if (bcs[k].performance > bBc[0][1]) {
//							bBc[1][0] = bBc[0][0]; // 2등이 1등 정보를 받고
//							bBc[1][1] = bBc[0][1];
//							bBc[0][1] = bcs[k].performance; // 1등 정보 갱신
//							bBc[0][0] = k;
//						} else if (bcs[k].performance > bBc[1][1]) {
//							bBc[1][1] = bcs[k].performance;
//							bBc[1][0] = k;
//						}
//					}
//				}
//
//				if (aBc[0][0] == bBc[0][0]) {
//					ans += aBc[0][1]; // 어차피 같으니깐..
//					ans += Math.max(aBc[1][1], bBc[1][1]);
//				} else {
//					ans += aBc[0][1] + bBc[0][1];
//				}

				// 이동
				userA_Pos[0] += dx[userA_Move[i]];
				userA_Pos[1] += dy[userA_Move[i]];
				userB_Pos[0] += dx[userB_Move[i]];
				userB_Pos[1] += dy[userB_Move[i]];

			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	static class BC {
		int id;
		int x;
		int y;
		int coverage;
		int performance;

		public BC(int id, int x, int y, int coverage, int performance) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.coverage = coverage;
			this.performance = performance;
		}

		public boolean isCover(int x, int y) {
			return (Math.abs(x - this.x) + Math.abs(y - this.y)) <= this.coverage;
		}

		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", coverage=" + coverage + ", performance=" + performance + "]";
		}

	}

}
