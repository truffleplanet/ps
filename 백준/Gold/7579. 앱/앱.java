import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		int maxCost = 10_000;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] value = new int[N];
		int[] cost = new int[N];
		int[][] dp = new int[2][maxCost + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		int prev = 0;
		for (int k = 0; k < N; k++) {
			int cur = prev ^ 1;
			int val = value[k];
			int cos = cost[k];
			for (int c = 0; c <= maxCost; c++) {
				if (c < cos)
					dp[cur][c] = dp[prev][c];
				else
					dp[cur][c] = Math.max(dp[prev][c], dp[prev][c - cos] + val);

				if (dp[cur][c] >= M) {
					maxCost = c;
					break;
				}
			}
			prev = cur;
		}

		System.out.println(maxCost);
	}

}
