/*
 * 타일링 문제와 거의 비슷하다.
 * 
 * i - 1 에 1붙이기 
 * i - 2 에 2 붙이기
 * i - 3 에 3 붙이기 
 * 
 * 로 i를 표현할 수 있다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_N = 11;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[] dp = new int[MAX_N + 1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1 + dp[1];
		dp[3] = 1 + dp[1] + dp[2];

		for (int i = 4; i <= MAX_N; i++) {
			dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
		}

		int T = Integer.parseInt(br.readLine());
		for (int $ = 0; $ < T; $++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
		}

		System.out.println(sb);
	}

}
