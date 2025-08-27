import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static final int MAX_EDGES = 400_002;
	static Edge[] EDGE_POOL = new Edge[MAX_EDGES];
	static int CUR_E;
	static {
		for (int i = 0; i < MAX_EDGES; i++) {
			EDGE_POOL[i] = new Edge();
		}
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		private Edge() {

		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge getEdge(int to, int w) {
		Edge e = EDGE_POOL[CUR_E++];
		e.to = to;
		e.weight = w;
		return e;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			CUR_E = 0;

			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			List<List<Edge>> graph = new ArrayList<>();

			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph.get(u).add(getEdge(v, c));
				graph.get(v).add(getEdge(u, c));
			}

			long weightSum = 0;
			int cnt = 0;
			boolean visited[] = new boolean[V + 1];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (Edge e : graph.get(1)) {
				pq.offer(e);
			}
			visited[1] = true;
			cnt++;

			while (cnt < V) {
				Edge e = pq.poll();

				if (e == null)
					break;

				if (visited[e.to])
					continue;

				visited[e.to] = true;
				weightSum += e.weight;
				cnt++;

				for (Edge o : graph.get(e.to)) {
					if (visited[o.to])
						continue;
					pq.offer(o);
				}
			}

			sb.append("#").append(tc).append(" ").append(weightSum).append("\n");
		}

		System.out.println(sb);
	}

}
