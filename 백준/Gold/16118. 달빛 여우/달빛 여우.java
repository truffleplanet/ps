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
		int w;

		public Edge(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

		public Edge(int to, int w, int state) {
			super();
			this.to = to;
			this.w = w;
		}

	}

	static class HeapNode implements Comparable<HeapNode> {
		int u;
		long dist;
		boolean slow;

		public HeapNode(int u, long dist) {
			super();
			this.u = u;
			this.dist = dist;
		}

		public HeapNode(int u, long dist, boolean slow) {
			super();
			this.u = u;
			this.dist = dist;
			this.slow = slow;
		}

		@Override
		public int compareTo(HeapNode o) {
			return Long.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		List<List<Edge>> G = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			G.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) * 2;
			G.get(u).add(new Edge(v, d));
			G.get(v).add(new Edge(u, d));
		}

		long[] dist1 = new long[V + 1];
		Arrays.fill(dist1, Integer.MAX_VALUE);

		PriorityQueue<HeapNode> pq = new PriorityQueue<>();
		dist1[1] = 0;
		pq.offer(new HeapNode(1, 0));

		while (!pq.isEmpty()) {
			HeapNode node = pq.poll();
			int u = node.u;
			long d = node.dist;

			if (dist1[u] != d)
				continue;

			for (Edge e : G.get(u)) {
				int v = e.to;
				long nd = d + e.w;
				if (dist1[v] > nd) {
					dist1[v] = nd;
					pq.offer(new HeapNode(v, nd));
				}
			}
		}

		pq.clear();

		long[][] dist2 = new long[V + 1][2];
		for (int i = 0; i <= V; i++) {
			Arrays.fill(dist2[i], Integer.MAX_VALUE);
		}
		dist2[1][0] = 0;
		pq.offer(new HeapNode(1, 0, false));

		while (!pq.isEmpty()) {
			HeapNode node = pq.poll();
			int u = node.u;
			long d = node.dist;
			boolean slow = node.slow;

			if (slow && dist2[u][1] != d) {
				continue;
			}

			if (!slow && dist2[u][0] != d) {
				continue;
			}

			for (Edge e : G.get(u)) {
				int v = e.to;
				int diff = e.w;
				if (slow) {
					diff *= 2;
				} else {
					diff /= 2;
				}
				long nd = d + diff;
				if (slow) {
					if (dist2[v][0] > nd) {
						dist2[v][0] = nd;
						pq.offer(new HeapNode(v, nd, false));
					}
				} else {
					if (dist2[v][1] > nd) {
						dist2[v][1] = nd;
						pq.offer(new HeapNode(v, nd, true));
					}
				}
			}
		}

		int cnt = 0;
		for (int i = 2; i <= V; i++) {
			long fox = dist1[i];
			long wolf = Math.min(dist2[i][0], dist2[i][1]);
//			System.out.println(fox + " " + wolf);
			if (fox < wolf) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

}
