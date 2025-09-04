import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge {

		int to;
		int weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

	}

	static class Node implements Comparable<Node> {
		int id;
		int dist;

		public Node(int id, int dist) {
			super();
			this.id = id;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int INF = 1_000_000_000;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			List<Edge>[] G = new List[V + 1];

			for (int i = 1; i <= V; i++) {
				G[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int u = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				G[u].add(new Edge(v, w));
			}

			int[] dist = new int[V + 1];
			Arrays.fill(dist, INF);
			dist[start] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(start, 0));

			while (!pq.isEmpty()) {
				Node u = pq.poll();

				if (dist[u.id] != u.dist)
					continue;

				for (Edge e : G[u.id]) {
					int nw = u.dist + e.weight;
					int v = e.to;

					if (dist[v] > nw) {
						dist[v] = nw;
						pq.offer(new Node(v, nw));
					}
				}
			}

			int cnt = 0;
			int max = 0;

			for (int i = 0; i < dist.length; i++) {
				if (dist[i] != INF) {
					cnt++;
					max = Math.max(max, dist[i]);
				}
			}

			sb.append(cnt).append(" ").append(max).append("\n");

		}

		System.out.println(sb);
	}
}
