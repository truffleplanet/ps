import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	static int N, ingrs[][], ans, Upper;
	static boolean isSelected[];
	static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] tokens = br.readLine().split(" ");
			N = Integer.parseInt(tokens[0]);
			Upper = Integer.parseInt(tokens[1]);
			ingrs = new int[N][2];
			isSelected = new boolean[N];

			// 데이터 입력
			for (int i = 0; i < N; i++) {
				String[] ingr = br.readLine().split(" ");
				ingrs[i][0] = Integer.parseInt(ingr[0]);
				ingrs[i][1] = Integer.parseInt(ingr[1]);
			}

			Arrays.sort(ingrs, (o1, o2) -> {
				return o1[1] - o2[1];
			});

			ans = 0;
			foodSelector(0, 0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	public static void foodSelector(int value, int cals) {

		ans = Math.max(ans, value);

		for (int i = 0; i < N; i++) {
			if (isSelected[i] || cals + ingrs[i][1] > Upper)
				break;
			isSelected[i] = true;
			foodSelector(value + ingrs[i][0], cals + ingrs[i][1]);
			isSelected[i] = false;
		}
	}
}
