/*
 * 최대한 이동하면 되는데,
 * 모든 칸은 대문자 알파벳이니 26개임.
 * char - 'A'를 인덱스로 하는 boolean 배열 사용하며 방문체크 
 * 방문은 칸이 아닌 알파벳을 기준으로 하면 되겠다.
 * 
 * 가볼 수 있는 곳까지 사방탐색 dfs
 * 
 * 최선의 길이는 매 회차 기록하기.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int[] DR = { -1, 0, 1, 0 };
	static final int[] DC = { 0, 1, 0, -1 };

	static boolean[] visited;
	static char[][] grid;
	static int R;
	static int C;
	static int ans;

	static void dfs(int r, int c, int cnt) {
		ans = Math.max(ans, cnt);// 매번 최대값 갱신

		for (int d = 0; d < 4; d++) {
			int nr = r + DR[d];
			int nc = c + DC[d];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C)
				continue;

			char ch = grid[nr][nc];
			int idx = ch - 'A';
			// 이미 방문해본 알파벳이면 넘어가고요 .
			if (visited[idx])
				continue;

			// 아니면 dfs해봅니다.
			visited[idx] = true;
			dfs(nr, nc, cnt + 1);
			visited[idx] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		grid = new char[R][C];
		for (int r = 0; r < R; r++) {
			grid[r] = br.readLine().trim().toCharArray();
		}

		visited = new boolean[26]; // 'A' ~ 'Z'
		ans = 0;
		visited[grid[0][0] - 'A'] = true;
		dfs(0, 0, 1); // 첫 위치는 이미 방문처리 해뒀음. 따라서 cnt 1로 시작
		System.out.println(ans);

	}

}
