import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		long[][] adjMatrix = new long[V][V];

		for (int i = 0; i < V; i++) {
			Arrays.fill(adjMatrix[i], Long.MAX_VALUE);
			adjMatrix[i][i] = 0L;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			long w = Long.parseLong(st.nextToken());
			if (adjMatrix[u][v] == 0) {
				adjMatrix[u][v] = w;
			} else {
				adjMatrix[u][v] = Math.min(adjMatrix[u][v], w);
			}
		}

//		 여기부터 최단경로

		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (adjMatrix[i][k] == Long.MAX_VALUE || adjMatrix[k][j] == Long.MAX_VALUE)
						continue;

					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				long val = adjMatrix[i][j];
				if (val == Long.MAX_VALUE)
					val = 0;

				sb.append(val).append(' ');
			}
			if (i < V - 1) {
				sb.append('\n');
			}
		}

		System.out.println(sb);

	}
}
