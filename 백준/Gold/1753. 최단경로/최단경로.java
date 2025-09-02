import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 핵심 아이디어:
 * 그래프는 인접 리스트로 표현한다. 
 * 
 * dist 배열: 시작점에서 각 노드까지의 최단 거리 저장
 * 우선순위 큐: 노드는 현재까지의 거리와 노드 id를 저장. 항상 거리가 작은 노드부터 꺼냄.
 * 방문 처리: 큐에서 꺼낸 거리가 더 크면 스킵
 * 
 * 
 * 
 * 
 */

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
		long dist;

		public Node(int id, long dist) {
			super();
			this.id = id;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Long.compare(this.dist, o.dist);
		}
	}

	static final long INF = Long.MAX_VALUE / 4;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		List<List<Edge>> graph = new ArrayList<>();

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		for (int iter = 0; iter < E; iter++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Edge(v, w));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		long[] dist = new long[V + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node u = pq.poll();

			if (u.dist != dist[u.id])
				continue;

			for (Edge e : graph.get(u.id)) {
				int v = e.to;
				long nd = u.dist + e.weight;
				if (nd < dist[v]) {
					dist[v] = nd;
					pq.offer(new Node(v, nd));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
		}
		System.out.println(sb);
	}

}
