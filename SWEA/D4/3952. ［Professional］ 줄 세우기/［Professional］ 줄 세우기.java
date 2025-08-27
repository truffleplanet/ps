import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] inCount = new int[N + 1];

			List<List<Integer>> graph = new ArrayList<>();

			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph.get(u).add(v);
				inCount[v]++;
				// u -> v
			}

			int cnt = 0;

			sb.append("#").append(tc).append(" ");
			while (cnt < N) {
				for (int u = 1; u <= N; u++) {
					if (inCount[u] == 0) {
						inCount[u] = -1;
						cnt++;
						sb.append(u).append(" ");
						for (int v : graph.get(u)) {
							inCount[v]--;
						}
					}
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
