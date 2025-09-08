import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Cap = Integer.parseInt(st.nextToken());

			int[][] oRow = new int[2][Cap + 1];
			int prev = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int w = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int next = prev ^ 1;

				for (int c = 0; c <= Cap; c++) {

					if (c < w) {
						oRow[next][c] = oRow[prev][c];
						continue;
					}

					oRow[next][c] = Math.max(oRow[prev][c], v + oRow[prev][c - w]);

				}

				prev ^= 1;

			}

			sb.append('#').append(tc).append(' ').append(oRow[prev][Cap]).append('\n');
		}

		System.out.println(sb);
	}

}
