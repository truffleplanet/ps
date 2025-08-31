import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 100 1 50 100
 * 
 * 1 3
 * 2 3
 * 3 4
 *
 * 제약사항 .
 * 이동 경로를 잘 기록해야하는데, 
 * 어떤 노드에, 선수 건물이 두 개 이상이라면 
 * 그 중 비용이 가장 큰 것을 이전 비용으로 봐야 함.
 * 
 * 
 * 위상정렬 구현: 
 * 1. 들어가는 간선이 없는 정점에서 시작해서
 * 2. 해당 정점에서 나가는 간선을 모두 끊으며, 끊은 종점에 들어가는 간선이 없게 되었으면,
 * 3. 지금까지의 경로 비용 + 해당 종점의 비용을 함. 
 * 
 * 추가사항: 
 * 큐를 heap으로 쓰고, 비용이 낮은 순부터 방문하게 하면, 
 * 값에 문제가 안생긴다. 
 * 
 * 아니면.. bestTime을 기록해두는 테이블에, 간선이 0이되는 경우가 아니어도 값을 적어두고,
 * 간선이 0이 될 때까지 거기서 비교 연산으로 Max값 유지한다. 
 * 
 * 시간복잡도:
 * 실행시간: 1초
 * V은 최대 1000, E는 최대 100,000
 *  O(Vlog(E))아닌가 힙쓰면? 
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int $ = 0; $ < T; $++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[] buildTime = new int[V + 1];
			int[] bestTime = new int[V + 1];
			int[] inDegree = new int[V + 1];
			inDegree[0] = Integer.MAX_VALUE;
			List<List<Integer>> G = new ArrayList<>();

			// init
			for (int i = 0; i <= V; i++) {
				G.add(new ArrayList<>());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= V; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				G.get(u).add(v);
				inDegree[v]++;
			}

			int target = Integer.parseInt(br.readLine());

			Queue<Integer> Q = new ArrayDeque<>();
			for (int i = 1; i <= V; i++) {
				if (inDegree[i] == 0) {
					inDegree[i]--; // 방문처리
					Q.add(i);
					bestTime[i] = buildTime[i];
				}
			}

			while (!Q.isEmpty()) {
				int u = Q.poll();

				if (u == target) {
					break;
				}

				for (int v : G.get(u)) {
					bestTime[v] = Math.max(bestTime[v], bestTime[u] + buildTime[v]);
					if (--inDegree[v] == 0) {
						Q.add(v);
					}

				}
			}

			sb.append(bestTime[target]).append("\n");

		}
		System.out.println(sb);
	}
}
