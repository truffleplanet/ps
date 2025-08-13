/*
 * 완전탐색 worst case -> 10000 * 3^500  * 
 * 
 * 파이프끼리는 교차할 수 없음.
 * 그러므로 높이순으로 왼쪽과 오른쪽이 연결되는 것이 최적임
 * 왼쪽 아래 행부터 오른쪽 아래로 차례대로 연결 or 왼쪽 위부터 오른쪽 위로 차례대로 연결
 *
 * 각 시작점에서 출발하여, 이동 우선순위를 우상, 우, 우하 순으로 
 * case 1. 끝 열 도착시 연결 카운트
 * case 2. 가능한 경로가 없을 시, 가지치기 
 * case 3. 우상 - 우 - 우하 순으로 dfs
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] dr = { -1, 0, 1 };
	static char[][] map;
	static int R, C, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");

		R = Integer.parseInt(tokens[0]);
		C = Integer.parseInt(tokens[1]);
		ans = 0;

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			if (dfs(i, 0))
				ans++;
		}
		System.out.println(ans);

	}

	public static boolean dfs(int r, int c) {
		if (c == C - 1) { // base case
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int nr = r + dr[i];
			int nc = c + 1; // 항상 우측 이동

			if (nr < 0 || nr >= R || nc < 0 || nc >= C) // 범위 밖일 때 (아마 nc는 검사하지 않아도 될듯)
				continue;

			if (map[nr][nc] == '.') { // 길이 있을 때
				map[nr][nc] = 'x'; // 방문 처리 (이번에 실패해도, 다른 점이 여기 와서 성공 불가)
				if (dfs(nr, nc)) {
					return true; // base case의 true를 이어받아서 오는 형식
				}
			}
		}

		return false; // 더 갈 수 있는 경로가 없을 때의 묵시적 처리

	}
}
