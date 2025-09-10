/*
 * n = 0 - > k = 0
 * n = 1 - > k = 1
 * n = 2 - > k = 3
 * 
 * 이제 앞으로 일어나는 일은 3가지 케이스임.
 * - 바로 전 것에 2x1을 붙임
 * - 2차례 전 것에 1x2를 2x2 모양으로 만들어서 붙임 
 * - 2차례 전 것에 2x2를 붙임
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		dp[1] = 1;

		if (N == 1) {
			System.out.println(dp[N]);
			return;
		}

		dp[2] = 3;

		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
		}

		System.out.println(dp[N]);

	}

}
