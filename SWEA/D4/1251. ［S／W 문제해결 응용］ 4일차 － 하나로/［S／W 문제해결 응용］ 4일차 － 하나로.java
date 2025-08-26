import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 해저터널의 길이 -> 두 섬 간의 유클리드거리
 * 
 * Node는 좌표평면 위의 점으로 표현됨.
 * 
 * Solution
 * step1. 가능한 모든 edge를 만든다. (최대 1000*1000) weight도 계산한다.
 * step2. edge를 weight에 따라 정렬한다.
 * step3. kruskal
 * 
 * 1. graph를 matrix로 표현하면 어떻게 되나?
 * - edge를 weight에 따라 정렬할 수가 없음. list로 관리하는게 좋음. 
 * 2. 최종 결과에만 e를 곱하면 어떻게 되나? (문제 없음. 그 전의 연산 전부 합 연산임.)
 * 
 */

public class Solution {

	// node, edge 구현.
	static int MAX_VERTICES = 1_000;
	static int MAX_EDGES = MAX_VERTICES * MAX_VERTICES;
	static Point[] POINT_POOL = new Point[MAX_VERTICES];
	static Edge[] EDGE_POOL = new Edge[MAX_EDGES];
	static int CUR_P;
	static int CUR_E;
	static int N;

	static {
		for (int i = 0; i < MAX_VERTICES; i++)
			POINT_POOL[i] = new Point();
		for (int i = 0; i < MAX_EDGES; i++)
			EDGE_POOL[i] = new Edge();
	}

	static class Point {
		int id;
		int x;
		int y;

		private Point() {

		}

		private static double squareDist(Point a, Point b) {
			return Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2);
		}
	}

	static class Edge implements Comparable<Edge> {
		Point from;
		Point to;
		double weight;

		private Edge() {

		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}

	public static Point getPoint(int id, int x, int y) {
		Point out = POINT_POOL[CUR_P++];
		out.id = id;
		out.x = x;
		out.y = y;
		return out;
	}

	public static Edge getEdge(Point from, Point to) { // weight 계산 여기서 함.
		Edge out = EDGE_POOL[CUR_E++];
		out.from = from;
		out.to = to;
		out.weight = Point.squareDist(from, to); // 여기!
		return out;
	}

	// union find 구현
	static int[] parents;

	static int find(int id) {
		if (id == parents[id])
			return id;

		parents[id] = find(parents[id]);
		return parents[id];
	}

	static boolean union(int a, int b) {
		int a_root = find(a);
		int b_root = find(b);

		if (a_root == b_root)
			return false;

		parents[b_root] = a_root;
		return true;

	}

	public static void init() {
		CUR_P = 0;
		CUR_E = 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력
		// 테스트 케이스 수 T
		int T = Integer.parseInt(br.readLine());

		Point[] vertices = new Point[MAX_VERTICES];
		Edge[] edges = new Edge[MAX_EDGES];
		parents = new int[MAX_VERTICES];

		for (int tc = 1; tc <= T; tc++) {
			init(); // 초기화
			N = Integer.parseInt(br.readLine());
			StringTokenizer st1 = new StringTokenizer(br.readLine()); // x 좌표
			StringTokenizer st2 = new StringTokenizer(br.readLine()); // y 좌표
			double E = Double.parseDouble(br.readLine()); // 환경 부담 세율 E

			// 노드 입력
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st1.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				vertices[i] = getPoint(i, x, y);
			}

			// 가능한 edge쌍 전부 만들기
			// 가능한 쌍의 갯수는 sum(1..(N - 1)) 이므로, N * ((N-1)/2) 인데
			// 어차피 loop 돌아야하니 직접 카운트 하기로.

			int totalEdges = 0;
			for (int i = 0; i < N - 1; i++)
				for (int j = i; j < N; j++) {
					edges[totalEdges++] = getEdge(vertices[i], vertices[j]);
				}

			Arrays.sort(edges, 0, totalEdges);
			// make
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}

			double weightSum = 0;
			int edgeCnt = 0;

			for (int i = 0; i < totalEdges; i++) {
				if (union(edges[i].from.id, edges[i].to.id)) {
					edgeCnt++;
					weightSum += edges[i].weight;
				}

				if (edgeCnt == N - 1) {
					break;
				}
			}

			long ans = Math.round(weightSum * E);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
