import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp[i][j] = min(dp[i][k] + dp[k+1][j] + a[i][0] * a[k][1] * a[j][1])
 * 		for k in [i, j)
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] a = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			a[i][0] = Integer.parseInt(st.nextToken());
			a[i][1] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N][N];
		for (int len = 1; len < N; len++) {
			for (int i = 0; i + len < N; i++) {
				int j = i + len;
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + a[i][0] * a[k][1] * a[j][1]);
				}
			}
		}
		System.out.println(dp[0][N - 1]);
	}
}
