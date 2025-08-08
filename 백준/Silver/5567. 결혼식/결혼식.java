import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			String[] tokens = br.readLine().split(" ");
			int u = Integer.parseInt(tokens[0]);
			int v = Integer.parseInt(tokens[1]);
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		boolean[] visited = new boolean[n + 1];
		Deque<int[]> queue = new ArrayDeque<>();

		visited[1] = true;
		queue.offer(new int[] { 1, 0 });

		int cnt = 0;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int u = curr[0];
			int depth = curr[1];

			if (depth >= 2)
				continue;

			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					cnt++;
					queue.offer(new int[] { v, depth + 1 });
				}
			}
		}

		System.out.println(cnt);
	}
}
