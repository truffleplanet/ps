import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N][3];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		st = new StringTokenizer(br.readLine());
		dp[0][0] = Integer.parseInt(st.nextToken());
		dp[0][1] = Integer.parseInt(st.nextToken());
		dp[0][2] = Integer.parseInt(st.nextToken());

		int[] buffer = new int[3];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int d = 0; d < 3; d++) {
				buffer[d] = Integer.parseInt(st.nextToken());
			}

			for (int d = 0; d < 3; d++) {
				for (int k = 0; k < 3; k++) {
					if (k == d)
						continue;
					dp[i][d] = Math.min(buffer[d] + dp[i - 1][k], dp[i][d]);
				}
			}

		}

		int ans = Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));
		System.out.println(ans);

	}

}
