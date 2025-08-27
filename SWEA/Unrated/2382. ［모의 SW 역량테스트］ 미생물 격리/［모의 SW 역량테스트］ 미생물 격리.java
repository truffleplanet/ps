
/*
 * 만난다는 것은 이동 후로 판단해야 함. 이동 후에 한 셀에 있어야 함.
 * 해법 - id 작은 미생물부터 순회하며, 움직임 동작. 더 작은 id일 때만 반응하기.
 * 
 * 시작 위치에서 빨간 셀에 있는 경우 없으므로 시작부터 경계조건 판단 안해도 됨. 코드 간편해짐. 
 * 
 * 군집 클래스 구현. move 구현. 
 * 군집 배열로 id 접근 바로 가능하게 구현.
 * 이동방향 반전 구현 .
 * 
 * 
 * 빨간 셀 내에서 무한 순회할 일은 없다. 처음에 태두리는 제외하고 군집이 생성되니깐. 
 * 
 * 0은 기본 지도, 
 * 1부터 id.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] map;
	static Cluster[] clusters; // 군집 id로 바로 군집을 찾을 수 있게 하는 배열

	// up left right down. 3-dir로 반전
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static class Cluster {
		int r;
		int c;
		int dir;
		int size;
		int bucket;
		boolean isValid;

		public Cluster(int r, int c, int dir, int size) {
			super();
			switch (dir) {
			case 1:
				dir = 0;
				break;
			case 2:
				dir = 3;
				break;
			case 3:
				dir = 1;
				break;
			case 4:
				dir = 2;
				break;
			}

			this.r = r;
			this.c = c;
			this.dir = dir;
			this.size = size;
			this.bucket = 0;
			this.isValid = true;
		}

		public void move(int id) {
			if (!this.isValid)
				return;

			if (this.bucket > 0) {
				this.size += this.bucket;
				this.bucket = 0;
			}

			if (map[r][c] == id) {
				map[r][c] = 0;
			}

			r += dr[dir];
			c += dc[dir];

			if (r == 0 || c == 0 || r == N - 1 || c == N - 1) {
				dir = 3 - dir;
				this.size = this.size / 2;
			} // 빨간 셀일 때 방향 전환. 다음 이동 때 탈출 보장.

			// 3개 이상의 군집이 모일수도 있으므로 거짓임 3개 이상 군집이 모인다면..?
			if (map[r][c] > 0 && map[r][c] < id) { // 1 based idx
				Cluster target = clusters[map[r][c]];
				if (target.size > this.size) {
					target.bucket += this.size;
					target.bucket += this.bucket;
					this.isValid = false;
				} else {
					this.bucket += target.size;
					this.bucket += target.bucket;
					target.isValid = false;
					map[r][c] = id;
				}
			} else {
				map[r][c] = id;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()); // time
			int K = Integer.parseInt(st.nextToken()); // 군집 수

			map = new int[N][N];
			clusters = new Cluster[K + 1];

			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				clusters[i] = new Cluster(r, c, dir, size);
				map[r][c] = i;
			}

			for (int t = 0; t < M; t++) {
				for (int i = 1; i <= K; i++) {
					if (clusters[i].isValid)
						clusters[i].move(i);
				}
			}

			int sum = 0;
			for (int i = 1; i <= K; i++) {
				if (clusters[i].isValid) {
					sum += clusters[i].size;
					sum += clusters[i].bucket;
				}
			}

			sb.append("#").append(tc).append(" ").append(sum).append("\n");

		}

		System.out.println(sb);
	}

//	static void printMap() {
//		for (int[] k : map) {
//			System.out.println(Arrays.toString(k));
//		}
//	}
}
