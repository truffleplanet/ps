import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 파이프의 상태. 0, 45, 90
 * 
 * 상태에 따라 이동방식 다름.
 * 
 * 한번에 90도 바뀌는 경우는 없고.. 45도씩 옮길수 있는 개념임. 
 * 
 * 이동의 형태는 그런데 3개뿐임. 이동 형태에 따른 체크해야되는 것 다름.
 * 
 * DAG 형태로 출발지에서 끝까지 가는데 
 * 순서가 있으므로 dfs 완전 탐색을 하든 bfs를 하든 같고..
 * 
 * 이동 종류 별 검사해야하는 블럭 델타만 잘 규정해놓고..
 * 
 * 상태로 이동 방식을 node로 들고 다니고..
 * 
 * 
 * dfs하자.
 * 
 */

public class Main {

	static final int R = 0;
	static final int RD = 1;
	static final int D = 2;

	/*
	 * 오른쪽 가면, 무조건 - 아래 가면 무조건 | 대각가면 무조건 \
	 */

	static boolean[][] isRoad;
	static int N;
	static int[][][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		isRoad = new boolean[N][N];
		int sr = 0;
		int sc = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				char ch = st.nextToken().charAt(0);
				if (ch == '0') {
					isRoad[i][j] = true;
				}
			}
		}

		dp = new int[N][N][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		System.out.println(dfs(sr, sc, R));

	}

	static int dfs(int r, int c, int dir) {

		if (r == N - 1 && c == N - 1) {
			return 1;
		}
		if (dp[r][c][dir] != -1)
			return dp[r][c][dir];

		int ways = 0;
		if (dir == R) {
			if (canRight(r, c))
				ways += dfs(r, c + 1, R);
			if (canDiag(r, c))
				ways += dfs(r + 1, c + 1, RD);
		} else if (dir == RD) {
			if (canRight(r, c))
				ways += dfs(r, c + 1, R);
			if (canDiag(r, c))
				ways += dfs(r + 1, c + 1, RD);
			if (canDown(r, c))
				ways += dfs(r + 1, c, D);
		} else if (dir == D) {
			if (canDiag(r, c))
				ways += dfs(r + 1, c + 1, RD);
			if (canDown(r, c))
				ways += dfs(r + 1, c, D);
		}

		return dp[r][c][dir] = ways;
	}

	static boolean canRight(int r, int c) {
		int nc = c + 1;
		return nc < N && isRoad[r][nc];
	}

	static boolean canDiag(int r, int c) {
		int nr = r + 1;
		int nc = c + 1;
		return nr < N && nc < N && isRoad[r][nc] && isRoad[nr][c] && isRoad[nr][nc];
	}

	static boolean canDown(int r, int c) {
		int nr = r + 1;
		return nr < N && isRoad[nr][c];
	}

}
