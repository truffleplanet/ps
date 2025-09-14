import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		dp = new int[N + 1];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			if (i - 2 >= 0 && dp[i - 2] != Integer.MAX_VALUE) {
				dp[i] = Math.min(dp[i], dp[i - 2] + 1);
			}
			if (i - 5 >= 0 && dp[i - 5] != Integer.MAX_VALUE) {
				dp[i] = Math.min(dp[i], dp[i - 5] + 1);
			}
		}

		if (dp[N] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dp[N]);
		}

	}
//
//	static void dfs(int cur) {
//		if (cur - 2 >= 0 && dp[cur - 2] > dp[cur] + 1) {
//			dp[cur - 2] = dp[cur] + 1;
//			dfs(cur - 2);
//		}
//
//		if (cur - 5 >= 0 && dp[cur - 5] > dp[cur] + 1) {
//			dp[cur - 5] = dp[cur] + 1;
//			dfs(cur - 5);
//		}
//	}
}
