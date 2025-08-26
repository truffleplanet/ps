import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 1. 각 노드는 2차원 좌표로 주어진다.
 * 2. 노드 간의 거리는 맨해튼 거리로 계산한다. 방향성은 없다.
 * 3. 완전 연결 그래프이다.
 * 4. 출발과 도착이 정해져있다. 첫 정점은 반드시 회사여야하고, 끝 정점은 반드시 집이어야 한다.
 * 5. 같은 곳을 두 번 방문하는게 최적일 수는 없느냐고 반론할 수도 있지만(중간 경로에 집이 끼어있는 경우),
 * 6. 정점 간 연결에 제한이 없으므로, 그럴일은 없다. 즉 각 노드별로 한번 씩만 방문한다. 
 * 
 * 
 * 
 */

public class Solution {

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		static int getDist(Node a, Node b) {
			return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
		}
	}

	static int N;
	static int best;
	static int[][] graph;
	static boolean[] visited;

	public static void dfs(ArrayList<Integer> path, int sum) {
		if (sum >= best) {
			return;
		}

		int last = path.get(path.size() - 1);

		if (path.size() == N - 1) {
			sum += graph[last][1];
			best = Math.min(sum, best);
			return;
		}

		for (int i = 2; i < N; i++) {
			if (visited[i])
				continue;

			ArrayList<Integer> out = (ArrayList<Integer>) path.clone();
			out.add(i);
			visited[i] = true;
			dfs(out, sum + graph[last][i]);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()) + 2; // +2
			st = new StringTokenizer(br.readLine());

			Node[] nodes = new Node[N];
			for (int i = 0; i < N; i++) {
				nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			graph = new int[N][N];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					int dist = Node.getDist(nodes[i], nodes[j]);
					graph[i][j] = graph[j][i] = dist;
				}
			}
//			for (int[] x : graph) {
//				System.out.println(Arrays.toString(x));
//			}

			// 0번 노드에서 1번 노드로 끝나는 것은 고정이고
			// 중간의 2번 노드부터 N번 노드까지 선택하는 모든 경우의 수 해보면서
			// 근데 이전 것 보다 합 크면 안하고 싶음.

			best = Integer.MAX_VALUE;
			ArrayList<Integer> path = new ArrayList<>();
			path.add(0);

			dfs(path, 0);

			sb.append("#").append(tc).append(" ").append(best).append("\n");
		}
		System.out.println(sb);
	}
}
