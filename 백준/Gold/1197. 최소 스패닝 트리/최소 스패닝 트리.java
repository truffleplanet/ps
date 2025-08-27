import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

//		@Override
//		public String toString() {
//			return "Edge [to=" + to + ", weight=" + weight + "]";
//		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()) + 1; // 1 based
		int E = Integer.parseInt(st.nextToken());

		List<List<Edge>> graph = new ArrayList<>();

		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Edge(to, weight));
			graph.get(to).add(new Edge(from, weight));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (Edge e : graph.get(1)) {
			pq.offer(e);
		}

		boolean[] visited = new boolean[V];
		int total = 0;
		int cnt = 1;
		visited[1] = true;

		while (cnt < V - 1) {
			Edge e = pq.poll();
			if (visited[e.to])
				continue;

			visited[e.to] = true;
			total += e.weight;
			cnt++;

			for (Edge n : graph.get(e.to)) {
				if (!visited[n.to])
					pq.offer(n);
			}
		}

		System.out.println(total);

	}

}
