import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] pop;
	static List<List<Integer>> graph;
	static int FULL;
	static int total;
	static int V;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine().trim());
		pop = new int[V];
		graph = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}

		total = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < V; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
			total += pop[i];
		}

		for (int u = 0; u < V; u++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				int v = Integer.parseInt(st.nextToken()) - 1;
				if (graph.get(u).contains(v))
					continue;
				graph.get(u).add(v);
			}
		}

		FULL = (1 << V) - 1;
		int ans = solve();

		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	static int solve() {
		int best = Integer.MAX_VALUE;

		for (int mask = 1; mask < FULL; mask++) {
			int other = FULL ^ mask;

			if (!isConnected(mask))
				continue;
			if (!isConnected(other))
				continue;

			int sumA = sum(mask);
			int sumB = total - sumA;
			best = Math.min(best, Math.abs(sumA - sumB));
		}
		return best;
	}

	static boolean isConnected(int mask) {
		if (mask == 0)
			return false;

		int start = -1;
		// mask에 속한 아무 정점이나 시작점으로
		for (int i = 0; i < V; i++) {
			if ((mask & (1 << i)) != 0) {
				start = i;
				break;
			}
		}
		if (start == -1)
			return false;

		// bfs

		boolean[] visited = new boolean[V];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		int cnt = 1;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if ((mask & (1 << v)) == 0)
					continue;
				if (visited[v])
					continue;

				visited[v] = true;
				cnt++;
				q.add(v);
			}
		}

		return cnt == Integer.bitCount(mask);
	}

	static int sum(int mask) {
		int s = 0;
		for (int i = 0; i < V; i++) {
			if ((mask & (1 << i)) != 0)
				s += pop[i];
		}
		return s;
	}

}
