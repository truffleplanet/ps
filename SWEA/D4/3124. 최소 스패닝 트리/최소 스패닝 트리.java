import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static final int MAX_VERTICES = 100_001;
	static final int MAX_EDGES = 200_001;
	static int[] parents = new int[MAX_VERTICES];
	static Edge[] EDGE_POOL = new Edge[MAX_EDGES];
	static int CUR_E;
	static {
		for (int i = 0; i < MAX_EDGES; i++) {
			EDGE_POOL[i] = new Edge();
		}
	}

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge getEdge(int x, int y, int w) {
		Edge e = EDGE_POOL[CUR_E++];
		e.from = x;
		e.to = y;
		e.weight = w;
		return e;
	}

	static void make(int N) {
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
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

		parents[x_root] = y_root;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		Edge[] edges = new Edge[MAX_EDGES];

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			// init
			make(V);
			CUR_E = 0;

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edges[i] = getEdge(u, v, c);
			}

			Arrays.sort(edges, 0, E);

			long weightSum = 0;
			int edgeCnt = 0;
			for (int i = 0; i < E; i++) {
				if (union(edges[i].from, edges[i].to)) {
					edgeCnt++;
					weightSum += edges[i].weight;
				}

				if (edgeCnt == E - 1)
					break;
			}

			sb.append("#").append(tc).append(" ").append(weightSum).append("\n");
		}

		System.out.println(sb);
	}

}
