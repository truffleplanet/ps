import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 입력부
			String[] line1 = br.readLine().split(" ");
			int N = Integer.parseInt(line1[0]);
			int B = Integer.parseInt(line1[1]);
			String[] tokens = br.readLine().split(" ");
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(tokens[i]);
			}

			// 1. brute-force
			int ans = Integer.MAX_VALUE;
			int x = 1 << (N + 1) - 1;
			for (int i = 0; i < x; i++) {
				int sum = 0;
				String indices = Integer.toBinaryString(i);

				// 뒤부터 읽을 것이고,
				// n자릿수 값이 1이면 arr[n-1]이 포함된 집합임.
				for (int k = indices.length() - 1, idx = 0; k >= 0; k--, idx++) {
					if (indices.charAt(k) == '1') {
						sum += arr[idx];
					}
				}
				if (sum >= B) {
					ans = Math.min(ans, sum);
				}
			}

            ans = ans - B;
			sb.append("#" + t + " " + ans).append("\n");

		}

		System.out.println(sb);

	}

}
