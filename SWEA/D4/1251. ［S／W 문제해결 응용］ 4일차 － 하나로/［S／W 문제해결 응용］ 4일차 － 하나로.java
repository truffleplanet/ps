import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static double squareDist(Node a, Node b) {
		return Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

//		System.out.println((Math.pow(1_000_000, 2) * 2) > Integer.MAX_VALUE); -> true

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 노드의 수
			StringTokenizer st1 = new StringTokenizer(br.readLine()); // x 좌표
			StringTokenizer st2 = new StringTokenizer(br.readLine()); // y 좌표
			double E = Double.parseDouble(br.readLine()); // 환경 부담 세율 E

			Node[] nodes = new Node[N];
			double[][] adjMatrix = new double[N][N];

			// 노드 입력
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st1.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				nodes[i] = new Node(x, y);
			}

			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double w = squareDist(nodes[i], nodes[j]);
					adjMatrix[i][j] = w;
					adjMatrix[j][i] = w;
				}
			}

			double weightSum = 0;
			double[] minEdges = new double[N];
			boolean[] visited = new boolean[N];

			Arrays.fill(minEdges, Double.POSITIVE_INFINITY);
			minEdges[0] = 0;

			for (int c = 0; c < N; c++) {

				double minWeight = Double.POSITIVE_INFINITY;
				int minVertex = 0;

				for (int i = 0; i < N; i++) {
					if (!visited[i] && minEdges[i] < minWeight) {
						minVertex = i;
						minWeight = minEdges[i];
					}
				}

				visited[minVertex] = true;
				weightSum += minWeight;

				for (int i = 0; i < N; i++) {
					if (!visited[i] && adjMatrix[minVertex][i] < minEdges[i]) {
						minEdges[i] = adjMatrix[minVertex][i];
					}
				}
			}
			long ans = Math.round(weightSum * E);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
