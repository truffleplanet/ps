import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 0/1 knapsack
 * 
 * 
 */

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[][] vec = new int[N][2]; // {val, cal}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int val = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				vec[i][0] = val;
				vec[i][1] = cal;
			}

			int[][] dp = new int[N][L + 1];
			int val0 = vec[0][0];
			int cal0 = vec[0][1];
			for (int j = cal0; j <= L; j++) {
				dp[0][j] = val0;
			}

			for (int i = 1; i < N; i++) {
				int val = vec[i][0];
				int cal = vec[i][1];
				for (int j = 0; j <= L; j++) {
					if (j >= cal) {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cal] + val);
					} else {
						dp[i][j] = dp[i - 1][j];
					}

				}
			}
			sb.append("#").append(tc).append(" ").append(dp[N - 1][L]).append("\n");

		}

		System.out.println(sb);
	}

}
