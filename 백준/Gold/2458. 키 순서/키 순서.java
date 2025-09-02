import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		List<List<Integer>> rgraph = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
			rgraph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			graph.get(u).add(v);
			rgraph.get(v).add(u);
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			int cnt = bfs(graph, i);
			int rcnt = bfs(rgraph, i);

			if (cnt + rcnt == N - 1) {
				ans++;
			}
		}

		System.out.println(ans);
	}

	static int bfs(List<List<Integer>> g, int s) {
		boolean[] visited = new boolean[g.size()];

		Queue<Integer> q = new ArrayDeque<>();
		visited[s] = true;
		q.offer(s);
		int cnt = 0;

		while (!q.isEmpty()) {
			int u = q.poll();

			for (int v : g.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					cnt++;
					q.offer(v);
				}
			}
		}
		return cnt;
	}
}
