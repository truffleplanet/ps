import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int N, ans;
	static int[] weights, selected;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			String[] tokens = br.readLine().split(" ");
			weights = new int[N];
			selected = new int[N];
			isSelected = new boolean[N];
			ans = 0;

			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(tokens[i]);
			}

			solution(0);
			sb.append("#" + t + " " + ans).append("\n");
		}
		System.out.println(sb);
	}

	public static void solution(int cnt) {

		if (cnt == N) {
			count(selected[0], 0, 1);
			return;
		}

		for (int i = 0; i < N; i++) { // 순열 만들기
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			selected[cnt] = weights[i];
			solution(cnt + 1);
			isSelected[i] = false;
		}

	}

	public static void count(int left, int right, int pos) {

		if (pos == N) {
			ans += 1;
			return;

		}

		for (int i = 0; i <= 1; i++) {
			if (i == 0) {
				left += selected[pos];
				count(left, right, pos + 1);
			} else {
				left -= selected[pos];
				right += selected[pos];
				if (right > left) {
					return;
				}
				count(left, right, pos + 1);
			}
		}
	}

}
