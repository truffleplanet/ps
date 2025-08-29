/*
 * 1. 손아귀보다 먼저 움직이기가 허용됨
 * 2. 그러나 다음턴에 손아귀가 다가와서 덮칠 곳에 가는 건 안됨. 
 * 3. 손아귀의 움직임을 계산해둔 테이블을 분리해서 보관해두고 그 테이블에는 시간별 손아귀의 위치가 기록되어 있음.
 * 
 * 해당 테이블의 초기값은 Integer.MaxValue,
 * 여신 위치는 그냥 따로 관리.
 * 돌은 Integer.MinValue;
 * 현재 내 시간이 table에 적힌 시간보다 작으면 이동 가능한 형태로 구성.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int[] DR = { 1, -1, 0, 0 };
		final int[] DC = { 0, 0, 1, -1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			final int GODDESS = -7777777;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int map[][] = new int[N][M];
			int[] start = new int[3];
			Queue<int[]> q = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				Arrays.fill(map[i], Integer.MAX_VALUE);
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					char c = line.charAt(j);
					if (c == 'S') {
						start[0] = i;
						start[1] = j;
						start[2] = 0; // time
					} else if (c == 'D') {
						map[i][j] = GODDESS;
					} else if (c == '*') {
						q.offer(new int[] { i, j });
						map[i][j] = 0;
					} else if (c == 'X') {
						map[i][j] = Integer.MIN_VALUE;
					}
				}
			} // 입력 완료

			// 물 시뮬레이션
			while (!q.isEmpty()) {
				int[] u = q.poll();
				int r = u[0];
				int c = u[1];
				for (int k = 0; k < 4; k++) {
					int nr = r + DR[k];
					int nc = c + DC[k];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					if (map[nr][nc] == Integer.MAX_VALUE) {
						map[nr][nc] = map[r][c] + 1;
						q.offer(new int[] { nr, nc });
					}
				}
			}

//			printMap(map);

			q.clear();
			// 이동 시뮬레이션
			q.offer(start);
			map[start[0]][start[1]] = -1;

			int bestTime = -1;
			while (!q.isEmpty()) {
				if (bestTime != -1)
					break;
				int[] u = q.poll();
				int r = u[0];
				int c = u[1];
				int nt = u[2] + 1;
				for (int k = 0; k < 4; k++) {
					int nr = r + DR[k];
					int nc = c + DC[k];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					if (map[nr][nc] == GODDESS) {
						bestTime = nt;
						break;
					}

					if (map[nr][nc] <= nt)
						continue;

					q.offer(new int[] { nr, nc, nt });
					map[nr][nc] = -1;
				}
			}

			sb.append("#").append(tc).append(" ");
			if (bestTime == -1) {
				sb.append("GAME OVER");
			} else {
				sb.append(bestTime);
			}
			sb.append("\n");

		}
		System.out.println(sb);
	}

	public static void printMap(int[][] map) {
		for (int[] m : map) {
			System.out.println(Arrays.toString(m));
		}
	}

}
