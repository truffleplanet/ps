import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);

		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

	static int find(int x) {
		if (x == parents[x])
			return x;

		parents[x] = find(parents[x]);
		return parents[x];
	}

	static boolean union(int x, int y) {
		int x_root = find(x);
		int y_root = find(y);

		if (x_root == y_root)
			return false;

		parents[y_root] = x_root;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		Edge[] edges = new Edge[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u, v, weight);
		}

		Arrays.sort(edges);

		int cnt = 0;
		int ans = 0;
		for (Edge e : edges) {
			if (union(e.from, e.to)) {
				cnt++;
				ans += e.weight;
			}

			if (cnt == N - 1) {
				break;
			}
		}

		System.out.println(ans);
	}
}
