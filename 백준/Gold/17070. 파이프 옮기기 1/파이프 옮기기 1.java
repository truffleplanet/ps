import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	static int[][] grid;
	static int N;
	static int ans;

	static class Node {
		int r;
		int c;
		int state;

		public Node(int r, int c, int state) {
			super();
			this.r = r;
			this.c = c;
			this.state = state;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		int sr = 0;
		int sc = 1;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		dfs(new Node(0, 1, R));
		System.out.println(ans);

	}

	static boolean canMove(Node x, int mType) {
		int r = x.r;
		int c = x.c;

		switch (mType) {
		case R: {
			int nc = c + 1;
			return nc < N && grid[r][nc] == 0;
		}
		case RD: {
			int nr = r + 1;
			int nc = c + 1;
			return nr < N && nc < N && grid[nr][nc] == 0 && grid[r][nc] == 0 && grid[nr][c] == 0;
		}
		case D: {
			int nr = r + 1;
			return nr < N && grid[nr][c] == 0;
		}
		}
		return false;
	}

	static void dfs(Node x) {

		if (x.r == N - 1 && x.c == N - 1) {
			ans++;
			return;
		}

		switch (x.state) {
		case R:
			if (canMove(x, R))
				dfs(new Node(x.r, x.c + 1, R));
			if (canMove(x, RD))
				dfs(new Node(x.r + 1, x.c + 1, RD));
			break;
		case RD:
			if (canMove(x, R))
				dfs(new Node(x.r, x.c + 1, R));
			if (canMove(x, RD))
				dfs(new Node(x.r + 1, x.c + 1, RD));
			if (canMove(x, D))
				dfs(new Node(x.r + 1, x.c, D));
			break;
		case D:
			if (canMove(x, RD))
				dfs(new Node(x.r + 1, x.c + 1, RD));
			if (canMove(x, D))
				dfs(new Node(x.r + 1, x.c, D));
			break;
		}

	}

}
