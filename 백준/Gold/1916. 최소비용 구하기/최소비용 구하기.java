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
		long w;

		public Edge(int to, long w) {
			super();
			this.to = to;
			this.w = w;
		}
	}

	static class HeapNode implements Comparable<HeapNode> {
		int u;
		long dist;

		public HeapNode(int u, long dist) {
			super();
			this.u = u;
			this.dist = dist;
		}

		@Override
		public int compareTo(HeapNode o) {
			return Long.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());

		List<List<Edge>> G = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			G.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			G.get(from).add(new Edge(to, w));
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		long[] dist = new long[V + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[s] = 0;
		PriorityQueue<HeapNode> pq = new PriorityQueue<>();
		pq.offer(new HeapNode(s, 0));

		while (!pq.isEmpty()) {
			HeapNode node = pq.poll();
			int u = node.u;
			long d = node.dist;

			if (dist[u] != d)
				continue;

			if (u == e) {
				System.out.println(d);
				return;
			}

			for (Edge ed : G.get(u)) {
				int v = ed.to;
				long nd = d + ed.w;

				if (dist[v] > nd) {
					dist[v] = nd;
					pq.offer(new HeapNode(v, nd));
				}
			}
		}

	}
}
