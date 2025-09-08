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

			int[][] stuffs = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				stuffs[i][0] = Integer.parseInt(st.nextToken());
				stuffs[i][1] = Integer.parseInt(st.nextToken());
			}

			int[][] oRow = new int[2][Cap + 1];

			int prev = 0;

			for (int s = 0; s < N; s++) {
				int w = stuffs[s][0];
				int v = stuffs[s][1];
				int next = prev ^ 1;
				for (int c = 0; c <= Cap; c++) {
					if (c < w) {
						oRow[next][c] = oRow[prev][c];
						continue;
					}
					oRow[next][c] = Math.max(oRow[prev][c], v + oRow[prev][c - w]);
				}
//				for (int i = 0; i <= 1; i++) {
//					System.out.println(Arrays.toString(oRow[i]));
//				}
				prev ^= 1;
			}

			sb.append('#').append(tc).append(' ').append(oRow[prev][Cap]).append('\n');

		}

		System.out.println(sb);
	}

}
