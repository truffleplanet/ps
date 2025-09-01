import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 * 총 가능한 출발점 16개. 
 * 가능한 분기 총 4^6 (매번 4방향으로 6번 이동)
 * 모든 경우를 탐색해도 4의 8승으로 65,536.
 * 
 * 완전탐색으로 풀기로 한다.
 * 
 * 구현 세부사항:
 * 7자리 숫자이고 0으로 시작하든 말든, 무조건 7자리이니깐..
 * 00003과 0003이 구별되거나 하는 경우가 없다. 
 * 즉 string으로 관리 안하고, 숫자로 관리해도 된다. 
 * 
 * 숫자의 범위는 0 ~ 9,999,999이고, 가능한 최대 숫자는 65,536으로 
 * 이미 생성된 숫자를 체크할 때 set을 쓰는게 적절하지만,
 * 어차피 메모리를 256MB주니, 10,000,000 크기의 boolean배열 쓰기로 한다. (10MB밑)
 */

public class Solution {
	static final int MAX_SIZE = 10_000_000;
	static final int N = 4;
	static int[][] map;
	static boolean[] isBuilded;
	static int cnt;

	static int[] DR = { 1, -1, 0, 0 };
	static int[] DC = { 0, 0, 1, -1 };

	static void dfs(int r, int c, int val, int k) {
		val = val * 10 + map[r][c];

		if (k == 6) {
			if (!isBuilded[val]) {
				isBuilded[val] = true;
				cnt++;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + DR[i];
			int nc = c + DC[i];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			dfs(nr, nc, val, k + 1);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		map = new int[N][N];
		isBuilded = new boolean[MAX_SIZE];

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 격자 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// dfs 준비
			cnt = 0;
			Arrays.fill(isBuilded, false);

			// dfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 0, 0);
				}
			}

			sb.append("#").append(tc).append(" ").append(cnt).append("\n");

		}

		System.out.println(sb);

	}

}
	