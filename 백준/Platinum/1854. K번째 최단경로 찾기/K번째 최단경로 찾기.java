import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

		int[][] dist = new int[V + 1][K + 1];

		for (int i = 1; i <= V; i++) {
			Arrays.fill(dist[i], INF);
		}

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt(n -> n[1]));
		dist[1][1] = 0;
		pq.offer(new int[] { 1, 0 });

		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			int uId = u[0];
			int ud = u[1];

			boolean match = false;
			for (int k = 1; k <= K; k++) {
				if (ud == dist[uId][k]) {
					match = true;
					break;
				}
			}

			if (!match)// stale
				continue;

			for (int[] e : G[uId]) {
				int vId = e[0];
				int w = e[1];

				int nd = ud + w;

				if (nd < dist[vId][K]) {
					dist[vId][K] = nd;
					Arrays.sort(dist[vId], 1, K + 1);
					pq.offer(new int[] { vId, nd });
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			int kth = dist[i][K];
			if (kth == INF) {
				sb.append(-1);
			} else {
				sb.append(kth);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
