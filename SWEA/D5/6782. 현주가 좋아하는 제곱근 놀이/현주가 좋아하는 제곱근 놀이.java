import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			long n = Long.parseLong(br.readLine());
			int cnt = 0;

			if (n == 2) {
				sb.append("#").append(tc).append(" ").append(cnt).append("\n");
				continue;
			}

			while (n > 2) {
				long sqrt = (long) Math.sqrt(n);
				if (sqrt * sqrt == n) {
					n = sqrt;
					cnt++;
				} else {
					long nextSqrt = (sqrt + 1) * (sqrt + 1); // 다음 제곱수
					cnt += (nextSqrt - n);
					n = sqrt + 1;
					cnt++;
				}
			} // n == 2 가되어야만 while문이 끝난다.
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");

		}
		System.out.println(sb);
	}

}
