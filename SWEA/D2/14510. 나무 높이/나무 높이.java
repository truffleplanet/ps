/*
 * 속성:
 * - n번째 날은 n/2의 짝수일과 n/2 + n%2의 홀수일로 이루어져 있다. (단 / 는 버림 나눗셈)
 * - 나무 높이를 1 증가시키는 것은 홀수일에만 가능하다.
 * - 나무 높이를 2 증가시키는 것은 홀수일 2번 혹은 짝수일 1번으로 가능하다.
 * 
 */

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
			st = new StringTokenizer(br.readLine());

			int[] heights = new int[N];
			int maxH = 0;
			for (int i = 0; i < N; i++) {
				int h = Integer.parseInt(st.nextToken());
				heights[i] = h;
				maxH = Math.max(maxH, h);
			}

			int need1 = 0;
			int need2 = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				heights[i] = maxH - heights[i];
				sum += heights[i];
				need1 += heights[i] % 2;
				need2 += heights[i] / 2;
			}

			int lo = 0;
			int hi = (need1 + need2) * 2;

			// [lo, hi)
			while (lo < hi) {
				int mid = (lo + hi) / 2;

				int even = mid / 2;
				int odd = mid / 2 + mid % 2;

				if (odd < need1) {
					lo = mid + 1;
					continue;
				}

				int extra = (odd - need1) / 2;
				int twos = even + extra;

				if (need2 <= twos) {
					hi = mid;
				} else {
					lo = mid + 1;
				}

			}

			sb.append('#').append(tc).append(' ').append(hi).append('\n');
		}
		System.out.println(sb);
	}
}
