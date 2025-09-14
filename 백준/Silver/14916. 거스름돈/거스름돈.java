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
		dp[N] = 0;
		dfs(N);

		if (dp[0] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dp[0]);
		}

	}

	static void dfs(int cur) {
		if (cur - 2 >= 0 && dp[cur - 2] > dp[cur] + 1) {
			dp[cur - 2] = dp[cur] + 1;
			dfs(cur - 2);
		}

		if (cur - 5 >= 0 && dp[cur - 5] > dp[cur] + 1) {
			dp[cur - 5] = dp[cur] + 1;
			dfs(cur - 5);
		}
	}
}
