
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
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			boolean[][] adjMatrix = new boolean[N][N];

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				adjMatrix[b][a] = true;
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (adjMatrix[i][k] && adjMatrix[k][j])
							adjMatrix[i][j] = true;
					}
				}
			}

			int ans = 0;
			for (int i = 0; i < N; i++) {
				int cnt = 0;
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					else if (adjMatrix[i][j] || adjMatrix[j][i])
						cnt++;
					else
						break;
				}

				if (cnt == N - 1)
					ans++;
			}

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.println(sb);
	}

}
