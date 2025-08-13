import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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
			int B = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);

			// 여러 합 조합을 만들어보는데,
			// B보다 크면 그 분기는 멈추고
			// 그 외에 모든 경우가 빠짐없이
			int ans = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				Deque<int[]> stack = new ArrayDeque<>();
				if (arr[i] >= B) {
					ans = Math.min(ans, arr[i]);
					break;
				}
				stack.push(new int[] { i, arr[i] });
				while (!stack.isEmpty()) {
					int[] node = stack.pollLast();
					int cur = node[0];
					int sum = node[1];

					for (int k = cur + 1; k < N; k++) {
						if (sum + arr[k] >= B) {
							ans = Math.min(ans, sum + arr[k]);
							break;
						}
						stack.offerLast(new int[] { k, sum + arr[k] });
					}
				}
			}

			ans -= B;
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

}
