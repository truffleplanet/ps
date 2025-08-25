import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * solution 1 
 * bfs로 connected component 찾기
 * 
 * solution 2
 * disjoint set으로 생각하기.
 * union이 성공한 횟수 카운트.
 * 
 * solution ?
 * 같은 관계는 반복해서 주어지지 않으므로, 
 * 사실상 edge의 수에 따라, 바로 결정되는 정답들이 있을 것 같은데.. 
 * 
 */

public class Solution {

	public static int find(int[] parents, int x) {
		if (x == parents[x])
			return x;

		parents[x] = find(parents, parents[x]);
		return parents[x];
	}

	public static int union(int[] parents, int x, int y) {
		int x_root = find(parents, x);
		int y_root = find(parents, y);

		if (x_root == y_root)
			return 0;

		parents[x_root] = y_root;
		return 1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int[] parents = new int[101];

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// init
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			int cnt = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				cnt += union(parents, u, v);
			}

			int ans = N - cnt;

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}

}
