import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 항상 하는 일은 같음. 4개의 영역을 비교 후 같은 색이면 같은 색으로, 
 * 다른 색이면 분할하기 
 * 
 * 
 * 더 분할할 수 없는 단위로 분할하고, 거기서 네 개가 같은 지 확인. 
 * 다르면 각각의 수 더하기, 같으면 합쳐서. 
 * 
 * 어떤 정보를 들고 다녀야하지? [0, 0] ?
 * 
 * 구현이 잘 감이 안온다. 
 * 
 * 일단 [0, N/2) [2/N, N) 구간으로 나누면 4등분은 가능하다. 
 * 
 * 괴롭다..
 * 
 */

/*
 * 0 1 2 3
 * 
 * (0, 2) (0, 3)
 * 
 * halfR 0
 * halfR+1 1
 * 
 * halfC: 2
 * halfC+1: 3
 * 
 * (1, 2), (1, 3);
 * 
 * 
 * 공백 주어진 채로 입력이 들어왔는데 charArray로 만들면 어쩌자는거야.. 
 */

public class Main {

	static int[][] map;

	static int[] recursion(int startR, int endR, int startC, int endC) {

		// base case
		if (startR == endR && startC == endC) {
			if (map[startR][startC] == 0) {
				return new int[] { 1, 0 };
			} else {
				return new int[] { 0, 1 };
			}
		}

		// avg
		int halfR = (startR + endR) / 2; // 이렇게 구간잡으면 계산해보면 포함으로 잡아야 함.
		int halfC = (startC + endC) / 2;

		int[] n1 = recursion(startR, halfR, startC, halfC); // 좌상
		int[] n2 = recursion(startR, halfR, halfC + 1, endC); // 우상
		int[] n3 = recursion(halfR + 1, endR, startC, halfC); // 좌하
		int[] n4 = recursion(halfR + 1, endR, halfC + 1, endC); // 우하

		int zero = n1[0] + n2[0] + n3[0] + n4[0];
		int one = n1[1] + n2[1] + n3[1] + n4[1];
		if (zero == 0) {
			return new int[] { 0, 1 };
		} else if (one == 0) {
			return new int[] { 1, 0 };
		} else {
			return new int[] { zero, one };
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] ans = recursion(0, N - 1, 0, N - 1);

		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}

}
