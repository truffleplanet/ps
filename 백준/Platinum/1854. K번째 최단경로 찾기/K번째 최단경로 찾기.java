import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라를 하는데,
 * dist가 3칸인거임. 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		final int INF = 100_000_000;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<int[]>[] G = new List[V + 1];
		for (int i = 1; i <= V; i++) {
			G[i] = new ArrayList<>();
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			G[u].add(new int[] { v, w });
		}

		PriorityQueue<Integer>[] dist = new PriorityQueue[V + 1];
		for (int i = 1; i <= V; i++) {
			dist[i] = new PriorityQueue<>(Comparator.reverseOrder());
		}

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt(n -> n[1]));
		dist[1].offer(0);
		pq.offer(new int[] { 1, 0 });

		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			int uId = u[0];
			int ud = u[1];

			for (int[] e : G[uId]) {
				int vId = e[0];
				int nd = ud + e[1];

				if (dist[vId].size() < K) {
					dist[vId].offer(nd);
					pq.offer(new int[] { vId, nd });
				} else if (dist[vId].peek() > nd) {
					dist[vId].poll();
					dist[vId].offer(nd);
					pq.offer(new int[] { vId, nd });
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (dist[i].size() < K)
				sb.append(-1);
			else
				sb.append(dist[i].peek());

			sb.append("\n");
		}

		System.out.println(sb);
	}
}
