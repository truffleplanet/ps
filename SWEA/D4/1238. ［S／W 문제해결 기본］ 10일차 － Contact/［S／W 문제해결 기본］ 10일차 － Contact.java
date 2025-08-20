import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 유향
 * 
 */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 사용 안함.
			int start = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			Map<Integer, Set<Integer>> graph = new HashMap<>();

			while (st.hasMoreTokens()) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph.putIfAbsent(u, new HashSet<>());
				graph.get(u).add(v);
			}

			Queue<Node> q = new ArrayDeque<>();
			Set<Integer> visited = new HashSet<>();
			q.offer(new Node(start, 0));
			visited.add(start);
			Node last = new Node(0, 0);
			while (!q.isEmpty()) {
				Node u = q.poll();
				int depth = u.depth;

				if (depth > last.depth) {
					last = u;
				} else if (depth == last.depth && u.e > last.e) {
					last = u;
				}
				if (!graph.containsKey(u.e))
					continue;

				for (int v : graph.get(u.e)) {
					if (!visited.contains(v)) {
						q.offer(new Node(v, depth + 1));
						visited.add(v);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(last.e).append("\n");

		}
		System.out.println(sb);
	}

	static class Node {
		int e;
		int depth;

		public Node(int e, int depth) {
			super();
			this.e = e;
			this.depth = depth;
		}

	}

}
