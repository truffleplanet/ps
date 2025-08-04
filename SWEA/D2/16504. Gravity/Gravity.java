import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // 가로길이
			int[] arr = new int[N];
			int max = Integer.MIN_VALUE;
			String[] tokens = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(tokens[i]);
				arr[i] = n;
				max = Math.max(max, n);
			}

			int ans = 0;
			for (int i = 1, idx = 0; i <= max; i++) {
				int d = 0;
				if (arr[idx] >= i) {
					for (int j = idx + 1; j < N; j++) {
						if (arr[j] < i) {
							d += 1;
						}
					}
				} else {
					idx++;
					i--;
				}
				ans = Math.max(ans, d);
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}
