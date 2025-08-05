import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] paper = new int[2][n];
			String[] line1 = br.readLine().split(" ");
			String[] line2 = br.readLine().split(" ");

			for (int i = 0; i < n; i++) {
				paper[0][i] = Integer.parseInt(line1[i]);
				paper[1][i] = Integer.parseInt(line2[i]);
			}

			int[][] dp = new int[2][n];
			dp[0][0] = paper[0][0];
			dp[1][0] = paper[1][0];

			for (int i = 1; i < n; i++) {
				dp[0][i] = Math.max(paper[0][i] + dp[1][i - 1], dp[0][i - 1]);
				dp[1][i] = Math.max(paper[1][i] + dp[0][i - 1], dp[1][i - 1]);
			}
			System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
		}

	}

}
