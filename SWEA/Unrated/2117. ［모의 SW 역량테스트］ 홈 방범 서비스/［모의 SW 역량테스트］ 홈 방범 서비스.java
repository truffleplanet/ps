import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			List<Node> homes = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int v = Integer.parseInt(st.nextToken());
					if (v == 1) {
						homes.add(new Node(i, j));
					}
				}
			}

			int K = N + 1;
			int cnt = 0;
			for (int k = K; k >= 1; k--) {
				int cost = k * k + (k - 1) * (k - 1);
				for (int x = 0; x < N; x++) {
					for (int y = 0; y < N; y++) {
						int sum = 0;
						for (Node home : homes) {
							int dx = Math.abs(x - home.x);
							int dy = Math.abs(y - home.y);
							if (dx + dy <= k - 1) {
								sum++;
							}
						}
						if (sum * M >= cost) {
							cnt = Math.max(cnt, sum);
						}
					}
				}
				if (cnt != 0)
					break;
			}

			sb.append('#').append(tc).append(' ').append(cnt).append('\n');

		}

		System.out.println(sb);
	}

}
