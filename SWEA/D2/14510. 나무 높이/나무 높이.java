/*
 * 최소일 d에 가능하다면, d+1, d+2...일에도 당연히 가능
 * 이분 탐색으로 d를 구할 것임.
 * 
 * d에 따라 홀수일 짝수일의 수가 정해진다. 
 * d가 짝수면, d/2, d/2
 * d가 홀수면 d/2 + 1, d/2 
 * 
 * 해당 oddCap과 evenCap을 바탕으로 이분 탐색을 할 것임 .
 * 
 * 
 * 1 | 2이고 역은 성립하지 않는 성질을 이용해보자. 
 * 
 * 먼저 최대 높이 H와 각 나무 높이의 차의 배열 diff[]에서 
 * diff[i] % 2 == 1인 날을 모두 찾아 그 수를 센다.
 * 그 날들은 최소한 홀수날에 물을 한번 줘야 한다. 
 * 먼저 이러한 홀수 날의 수와 전체 합을 구한다. 
 * need1 = di % 2;
 * need2 = di / 2;
 * 
 * 나무 높이가 초과하면 안되므로, 잘 이분탐색해서 구해본다. 
 * 
 * d의 상한은 홀수날에만 물을 주는 경우로 2 * sum(diff) 로 잡는다. 
 * d의 하한은 0.
 * 
 * 이분 탐색 분기 
 * while (left < right) {
 * 	if oddCap이 need1보다 작으면 
 * 	우측 탐색 
 * 
 * 1의 조합으로 만들 수 있는 2의 수 구하기 
 * 	extraTwos = (oddCap - need1) / 2
 * 	avail2 = evenCap + extraTwos;
 * 	if need2 <= avail2:
 * 		 좌측 탐색
 * 		이렇게 좌측 해도 될 떄 계속 이동하다 mid가 left보다 작아지
 * 	else:
 * 		우측 탐색 
 * 		lo = mid + 1
 * }
 * 
 */

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

			List<Integer> diffs = new ArrayList<>();
			int need1 = 0;
			int need2 = 0;
			for (int i = 0; i < N; i++) {
				int d = H - heights[i];
				if (d > 0) {
					diffs.add(H - heights[i]);
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
