import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> adj = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(v).add(u); // v - > u | u가 v를 신뢰한다.
		}

		int best = 0;
		List<Integer> candidates = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			int cnt = 1;
			Queue<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N + 1];
			q.offer(i);
			visited[i] = true;

			while (!q.isEmpty()) {
				int u = q.poll();
				for (int v : adj.get(u)) {
					if (!visited[v]) {
						visited[v] = true;
						cnt++;
						q.offer(v);
					}
				}
			}
			if (cnt > best) {
				candidates = new ArrayList<>();
				candidates.add(i);
				best = cnt;
			} else if (cnt == best) {
				candidates.add(i);
			} else {
				continue;
			}
		}

		for (int c : candidates) {
			System.out.print(c + " ");
		}
	}

}
