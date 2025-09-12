/*
 * 계단:
 * 수용인원, 
 * 
 * 
 * 걍 완탐해~
 * 
 * 1. 각 사람과 양쪽 계단의 거리를 구한다. (재활용 해야하니)
 * 2. 사람을 2그룹으로 나눈다. (부분집합 혹은, 절반 조합)
 * 3. 계단 당 다 내려가려면 걸리는 시간 따로 구하고, 그중 최댓값이 내려가는데 걸린 시간.
 * 
 * 
 * 구현 주의점:
 * 계단의 입구에서 1초 대기한다는 건 어떻게 구현할 것임????
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int ans;
	static int stair0_L = 0;
	static int stair1_L = 0;
	static int[][] time;

	static void subset(boolean[] selected, int idx) {
		int N = selected.length;
		if (idx == N) {
			logic(selected);
//			for (int i = 0; i < N; i++) {
//				selected[i] = !selected[i];
//			}
//			logic(selected);
//			for (int i = 0; i < N; i++) {
//				selected[i] = !selected[i];
//			}
			return;
		}

		selected[idx] = true;
		subset(selected, idx + 1);
		selected[idx] = false;
		subset(selected, idx + 1);
	}

	static void logic(boolean[] selected) {
		int N = selected.length;

		ArrayList<Integer> time0 = new ArrayList<>();
		ArrayList<Integer> time1 = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				time0.add(time[0][i]);
			} else {
				time1.add(time[1][i]);
			}
		}
		Collections.sort(time0);
		Collections.sort(time1);

		for (int i = 0; i < time0.size(); i++) {
			if (i < 3) {
				int t = time0.get(i);
				t += stair0_L + 1;
				time0.set(i, t);
			} else {
				int t = time0.get(i);
				int prev = time0.get(i - 3);
				if (prev >= t + 1) {
					t = prev + stair0_L;
				} else {
					t += stair0_L + 1;
				}
				time0.set(i, t);
			}
		}

		for (int i = 0; i < time1.size(); i++) {
			if (i < 3) {
				int t = time1.get(i);
				t += stair1_L + 1;
				time1.set(i, t);
			} else {
				int t = time1.get(i);
				int prev = time1.get(i - 3);
				if (prev >= t + 1) {
					t = prev + stair1_L;
				} else {
					t += stair1_L + 1;
				}
				time1.set(i, t);
			}
		}

		int max1 = 0;
		int max2 = 0;

		if (time0.size() > 0)
			max1 = time0.get(time0.size() - 1);

		if (time1.size() > 0)
			max2 = time1.get(time1.size() - 1);

		int total = Math.max(max1, max2);

//		System.out.println(time0);
//		System.out.println(time1);
//		System.out.println(total);
		ans = Math.min(ans, total);

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			stair0_L = 0;
			stair1_L = 0;

			int stair0_r = 0;
			int stair0_c = 0;

			int stair1_r = 0;
			int stair1_c = 0;

			List<int[]> p = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int e = Integer.parseInt(st.nextToken());
					if (e == 1) {
						p.add(new int[] { i, j });
					} else if (e > 1) {
						if (stair0_L == 0) {
							stair0_L = e;
							stair0_r = i;
							stair0_c = j;
						} else {
							stair1_L = e;
							stair1_r = i;
							stair1_c = j;
						}
					}
				}
			} // 입력 종료

			int pSize = p.size();
			time = new int[2][pSize];
			for (int i = 0; i < pSize; i++) {
				int[] pos = p.get(i);
				int dist0 = Math.abs(stair0_r - pos[0]) + Math.abs(stair0_c - pos[1]);
				int dist1 = Math.abs(stair1_r - pos[0]) + Math.abs(stair1_c - pos[1]);
				time[0][i] = dist0;
				time[1][i] = dist1;
			} // 계단과 각 사람의 거리 구하기 종료

//			for (int i = 0; i < 2; i++) {
//				System.out.println(Arrays.toString(time[i]));
//			}

			ans = Integer.MAX_VALUE;
			boolean[] selected = new boolean[pSize];
			subset(selected, 0);
			sb.append('#').append(tc).append(' ').append(ans).append('\n');

		}

		System.out.println(sb);
	}
}
