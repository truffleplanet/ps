import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] tokens = br.readLine().split(" ");
			int N = Integer.parseInt(tokens[0]);
			int L = Integer.parseInt(tokens[1]);
			int[][] ingrs = new int[N][2];

			// 데이터 입력
			for (int i = 0; i < N; i++) {
				String[] ingr = br.readLine().split(" ");
				ingrs[i][0] = Integer.parseInt(ingr[0]); // val
				ingrs[i][1] = Integer.parseInt(ingr[1]); // cal
			}

			int ans = -1;

			for (int i = 0; i < (1 << N); i++) {
				int valSum = 0;
				int calSum = 0;
				for (int j = 0; j < N; j++) {
					if ((i & (1 << j)) != 0) {
						calSum += ingrs[j][1];
						if (calSum > L) {
							break;
						}
						valSum += ingrs[j][0];
						ans = Math.max(ans, valSum);
					}
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
