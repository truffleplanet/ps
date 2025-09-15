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
			sb.append('#').append(tc).append(' ');
			int N = Integer.parseInt(br.readLine());
			String[] seq = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				seq[i] = st.nextToken();
			}

			int half = N / 2 + N % 2;

			for (int i = 0; i < half; i++) {
				sb.append(seq[i]).append(' ');

				if (half + i < N)
					sb.append(seq[half + i]).append(' ');

			}

			sb.append('\n');

		}

		System.out.println(sb);
	}

}
