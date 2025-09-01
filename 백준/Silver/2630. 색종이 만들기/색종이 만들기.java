import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;

	static int[] recursion(int sR, int eR, int sC, int eC) {

		if (sR == eR && sC == eC) {
			if (map[sR][sC] == 0) {
				return new int[] { 1, 0 };
			} else {
				return new int[] { 0, 1 };
			}
		}

		int halfR = (sR + eR) / 2;
		int halfC = (sC + eC) / 2;

		int[] x1 = recursion(sR, halfR, sC, halfC);
		int[] x2 = recursion(sR, halfR, halfC + 1, eC);
		int[] x3 = recursion(halfR + 1, eR, sC, halfC);
		int[] x4 = recursion(halfR + 1, eR, halfC + 1, eC);

		int zero = x1[0] + x2[0] + x3[0] + x4[0];
		int one = x1[1] + x2[1] + x3[1] + x4[1];

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
