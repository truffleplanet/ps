import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			int[] heights = new int[N];
			int H = 0;
			for (int i = 0; i < N; i++) {
				int h = Integer.parseInt(st.nextToken());
				heights[i] = h;
				H = Math.max(h, H);
			}

			int need1 = 0;
			int need2 = 0;
			for (int i = 0; i < N; i++) {
				int d = H - heights[i];
				if (d > 0) {
					need1 += d % 2;
					need2 += d / 2;
				}
			}

			int hi = 2 * (need1 + need2);
			int lo = 0;
			while (lo < hi) {
				int mid = (lo + hi) / 2;
				int quotient = mid / 2;
				int oddCap = quotient + (mid % 2);
				int evenCap = quotient;

				if (oddCap < need1) {
					lo = mid + 1;
					continue;
				}

				int extraTwos = (oddCap - need1) / 2;
				int avail2 = evenCap + extraTwos;

				if (need2 <= avail2) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}

			sb.append("#").append(tc).append(" ").append(lo).append("\n");
		}

		System.out.println(sb);
	}

}
