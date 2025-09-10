import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 1_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[][] adjMatrix = new int[V][V];

		for (int i = 0; i < V; i++) {
			Arrays.fill(adjMatrix[i], INF);
			adjMatrix[i][i] = 0;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[u][v] = 1;
			adjMatrix[v][u] = 1;
		}

		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}

		int ans = -1;
		int min = INF;
		for (int i = 0; i < V; i++) {
			int sum = 0;
			for (int j = 0; j < V; j++) {
				sum += adjMatrix[i][j];
			}

			if (sum < min) {
				min = sum;
				ans = i + 1;
			}
		}

		System.out.println(ans);
	}

}
