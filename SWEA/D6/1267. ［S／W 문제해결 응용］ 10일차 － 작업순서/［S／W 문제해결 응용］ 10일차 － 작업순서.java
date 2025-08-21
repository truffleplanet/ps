import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = 10;

		List<List<Integer>> G = new ArrayList<>();

		for (int tc = 1; tc <= 10; tc++) {
			G.clear();

			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			for (int i = 0; i <= V; i++) {
				G.add(new ArrayList<>());
			}

			int[] D = new int[V + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				G.get(u).add(v);
				D[v]++;
			}

			sb.append("#").append(tc).append(" ");

			int cnt = 0;
			while (cnt < V) {
				for (int i = 1; i <= V; i++) {
					if (D[i] == 0) {
						D[i] = -1;
						cnt++;
						for (int v : G.get(i)) {
							D[v]--; // 실제로 간선을 제거하지는 않지만 마치 그런것처럼 처리 .
						}
						sb.append(i).append(" ");
						break;
					}
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
