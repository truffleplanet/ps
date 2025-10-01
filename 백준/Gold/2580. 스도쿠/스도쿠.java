/*
스도쿠는 np문제로 유명하니
그냥 완탐을 하면 되는데.. 구현이 중요한 문제다. 

0이 몇 개 들어있는지 알 수 없으므로,
얼마나 0이 많든 백트래킹하며 전부 채울 수 있는 알고리즘을 설계해야한다.
즉 row, col 별로 어떤 숫자들이 있나 관리해야한다.

isInRow? - [row][num + 1]
isInCol? - [col][num + 1]
isInBucket [3][3][num + 1]

boolean배열 두개 쓰면 되고..

빈칸 위치는 다 기억하고 시작하면 좋겠는데.
만약에 뭔 짓을 해도 안되면 이전 상태로 돌아가야하는데,
이전 상태로 돌아가는 것은, 함수딴에서 그냥 boolean 배열에 다시 false처리 하면 될 것이다

그러면 빈칸 배열 만들어놓고, 빈칸에 대해 순서대로... 
숫자 넣기.

만약에 판이 완성되면... 끝.

이번에는 전파 방식이 아니라,, 
일단 함수 호출 스택에 넣고
유망하지 않으면 가지 않는 식으로 해볼까..? 
-> 그러기엔 상태 전달이 복잡할 것 같다. 
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N = 9;
	static int[][] map;
	static boolean[][] isInRow;
	static boolean[][] isInCol;
	static boolean[][][] isInBucket;
	static List<Pos> zeros;
	static List<Integer> vals;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		map = new int[N][N];
		isInRow = new boolean[N][N + 1]; // 0 ~9, 1 ~ 9
		isInCol = new boolean[N][N + 1];
		isInBucket = new boolean[3][3][N + 1];
		zeros = new ArrayList<>();
		vals = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int k = Integer.parseInt(st.nextToken());
				map[i][j] = k;
				if (k == 0) {
					zeros.add(new Pos(i, j));
					continue;
				}
				isInRow[i][k] = true;
				isInCol[j][k] = true;
				isInBucket[i / 3][j / 3][k] = true; // {0,1,2}, {3,4,5}, {6,7,8};
			}
		} // 입력 종료

		// 로직
		backtrack(0);

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static boolean backtrack(int cnt) {
		if (cnt == zeros.size()) {
			// 스도쿠 업데이트 후 종료
			for (int i = 0; i < zeros.size(); i++) {
				Pos x = zeros.get(i);
				map[x.r][x.c] = vals.get(i);
			}
			return true;
		}

		Pos cur = zeros.get(cnt);
		int r = cur.r;
		int c = cur.c;
		int br = r / 3;
		int bc = c / 3;

		for (int i = 1; i <= 9; i++) {
			if (!isInRow[r][i] && !isInCol[c][i] && !isInBucket[br][bc][i]) {
				isInRow[r][i] = true;
				isInCol[c][i] = true;
				isInBucket[br][bc][i] = true;
				vals.add(i);
				if (backtrack(cnt + 1)) {
					return true;
				} else {
					vals.remove(vals.size() - 1);
					isInRow[r][i] = false;
					isInCol[c][i] = false;
					isInBucket[br][bc][i] = false;
				}
			}
		}
		return false;
	}

}