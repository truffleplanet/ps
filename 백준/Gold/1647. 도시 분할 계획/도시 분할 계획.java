import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * 11:35 시작 
 * 12:05 종료
 * 
 * 2개로 그래프를 나눈다고 해도, 원래 크루즈칼 하며
 * 간선 중 사이클 형성해서 고르지 않았을 간선을 고를 일이 없다.
 * 
 * 따라서 기존 mst 알고리즘에서, 마지막 간선을 선택하는 파트만 빼주면 된다.
 * 
 */

public class Main {

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

	}

	// disjoint set impl
	static HashMap<Integer, Integer> parents; // 집 번호 조건이 나와있지 않으므로 안전 상

	static int find(int x) {
		if (x == parents.get(x))
			return x;
		parents.put(x, find(parents.get(x))); // 주의
		return parents.get(x);
	}

	static boolean union(int x, int y) {
		int x_root = find(x);
		int y_root = find(y);

		if (x_root == y_root)
			return false;

		parents.put(x_root, y_root);
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		parents = new HashMap<>();
		Edge[] edges = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u, v, weight);
			// make
			parents.putIfAbsent(u, u);
			parents.putIfAbsent(v, v);
		}

		Arrays.sort(edges);

		long weightSum = 0;
		int edgeCnt = 0;

		for (Edge e : edges) {
			if (edgeCnt == V - 2) // 집이 두 개 뿐이면 시작에서 걸러져야 함.
				break;

			if (union(e.from, e.to)) {
				edgeCnt++;
				weightSum += e.weight;
			}
		}

		System.out.println(weightSum);

	}

}
