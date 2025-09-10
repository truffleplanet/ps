import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int INF = 10_000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());

			int[][] adjMatrix = new int[V][V];

			for (int i = 0; i < V; i++) {
				Arrays.fill(adjMatrix[i], INF);
				adjMatrix[i][i] = 0;
			}

			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					int val = Integer.parseInt(st.nextToken());
					if (val == 1) {
						adjMatrix[i][j] = 1;
					}
				}
			}

			for (int k = 0; k < V; k++) {
				for (int i = 0; i < V; i++) {
					for (int j = 0; j < V; j++) {
						if (adjMatrix[i][k] == INF || adjMatrix[k][j] == INF)
							continue;
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
					}
				}
			}

			int min = INF;
			for (int i = 0; i < V; i++) {
				int sum = 0;
				for (int j = 0; j < V; j++) {
					sum += adjMatrix[i][j];
				}

				if (sum < min) {
					min = sum;
				}
			}

			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}

		System.out.println(sb);
	}

}
