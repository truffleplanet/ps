
/*
 * (n - m + 1) x (n - m + 1) 배열로 표현 가능
 * m x m 만큼의 연산 매번 할 필요 없이, 매번 2m만큼만 연산하면 됨
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] line = br.readLine().split(" ");
			int N = Integer.parseInt(line[0]);
			int M = Integer.parseInt(line[1]);

			int[][] matrix = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] tokens = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(tokens[j]);
				}
			}

			int[][] dp = new int[N - M + 1][N - M + 1];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < M; j++) {
					dp[0][0] += matrix[i][j];
				}
			}

			int ans = dp[0][0];

			for (int i = 0; i < N - M + 1; i++) {

				if (i != 0) {
					dp[i][0] = dp[i - 1][0];
					for (int c = 0; c < M; c++) {
						dp[i][0] += matrix[i + M - 1][c];
						dp[i][0] -= matrix[i - 1][c];
					}
					ans = Math.max(ans, dp[i][0]);
				}

				for (int j = 1; j < N - M + 1; j++) {
					dp[i][j] = dp[i][j - 1];
					for (int r = 0; r < M; r++) {
						dp[i][j] += matrix[i + r][j + M - 1];
						dp[i][j] -= matrix[i + r][j - 1];
					}
					ans = Math.max(ans, dp[i][j]);
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}

}
