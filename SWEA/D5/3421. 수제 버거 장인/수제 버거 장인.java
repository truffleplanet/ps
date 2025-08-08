import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int M = Integer.parseInt(tokens[1]);

			boolean[][] graph = new boolean[N + 1][N + 1]; // true면 비연결, false면 연결

			for (int i = 0; i < M; i++) {
				tokens = br.readLine().split(" ");
				int u = Integer.parseInt(tokens[0]);
				int v = Integer.parseInt(tokens[1]);
				graph[u][v] = true;
				graph[v][u] = true;
			}

			cnt = 1;
			for (int i = 1; i < N + 1; i++) {
				Set<Integer> nodes = new HashSet<>();
				nodes.add(i);
				cnt++;
				dfs(i, N + 1, nodes, graph);
			}
			sb.append("#" + t + " " + cnt).append("\n");
		}
        System.out.println(sb);
	}

	public static void dfs(int start, int N, Set<Integer> nodes, boolean[][] graph) {

		for (int i = start + 1; i < N; i++) {
			if (nodes.contains(i)) {
				continue;
			} else {
				boolean connected = true;
				for (int u : nodes) {
					if (graph[u][i]) {
						connected = false;
						break;
					}
				}
				if (connected) {
					cnt++;
					nodes.add(i);
					dfs(i, N, nodes, graph);
					nodes.remove(i);
				}
			}
		}
	}

}
