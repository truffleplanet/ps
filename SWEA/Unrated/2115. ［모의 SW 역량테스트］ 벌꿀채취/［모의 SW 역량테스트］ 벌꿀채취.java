
/*
 * 제약조건: 
 * 반드시 가로로 M개의 벌통을 선택하고, 선택 한 후 고르는 것임.
 * 따라서 벌통 x를 선택하고 우측으로 M개를 골랐을 때 최적의 선택을 했을 시 점수를 기록해두는 테이블을 하나 만든다. 
 * 그리고 이제 고를 차례인데 어떤 결과를 먼저 고르면 다음에 같은 행에서 선택할 수 있는건 다음 조건에 부합해야 한다. 
 * 안 되는 조건:  x < 0 || x > N - M
 * 되는 조건: y >= x + M || y <= x - M
 * 
 * 해당 조건에 부합하는 것을 골라서 조합해볼 수 있음.
 * 
 * 이러한 이유로 탐욕적 접근은 어렵고.
 * 조합의 수를 낮춰서 해볼 것임.
 * 1. 먼저, 서로 다른 행에서 최대값 두 개를 고른다. 행이 다른 케이스에 최대가 있다면 여기서 끝.
 * 2. 각 같은 행들에서 가능한 모든 조합을 하며 점수를 구한다. 
 * 
 * 1, 2에서 나온 값을 비교하여 최대가 되는 값을 고른다. 
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int MAX_N = 10;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[][] src = new int[MAX_N][MAX_N];
		int[][] square = new int[MAX_N][MAX_N];

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 지도 크기
			int M = Integer.parseInt(st.nextToken()); // 벌통 선택 사이즈
			int C = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					src[i][j] = Integer.parseInt(st.nextToken());
					square[i][j] = src[i][j] * src[i][j];
				}
			}

			int[][] best = new int[N][N - M + 1];

			int binaryC = (1 << M);
			// i, j는 행과 열
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N - M + 1; j++) {

					// 여긴 바이너리 카운팅을 통한 부분집합 완전탐색
					for (int k = 0; k < binaryC; k++) {
						int sum = 0;
						int score = 0;
						for (int m = 0; m < M; m++) {
							if ((k & (1 << m)) != 0) {
								sum += src[i][j + m];
								score += square[i][j + m];
							}
						}
						if (sum > C)
							continue;
						best[i][j] = Math.max(best[i][j], score);
					}
				}
			}

			// 행 두개에서 따로 뽑을 경우 최대 값.
			int[] bestRow = new int[N];

			for (int i = 0; i < N; i++) {
				int first = 0;
				for (int j = 0; j < N - M + 1; j++) {
					if (best[i][j] > first)
						first = best[i][j];
				}
				bestRow[i] = first;
			}

			Arrays.sort(bestRow); // 커봤자 10이니 그냥..
			int ans = bestRow[N - 1] + bestRow[N - 2];

			// 같은 행일 경우 탐색
			/*
			 * 안 되는 조건: x < 0 || x > N - M 되는 조건: y >= x + M || y <= x - M
			 * 
			 */

			for (int i = 0; i < N; i++) {
				for (int x = 0; x < N - M + 1; x++) {
					int y = x + M;

					if (y > N - M)
						break;

					while (y <= N - M) {
						ans = Math.max(ans, best[i][x] + best[i][y++]);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);

	}
}