
/*
 * 1일 이용권
 * 1달 이용권 (매달 1일 시작)
 * 3달 이용권 (매달 1일 시작, 연속된 3달 이용)
 * 1년 이용권 (1월 1일 시작)
 * 
 * 각 달의 이용 계획이 주어지고, 최저 비용 찾기
 * 
 * main idea
 * - 먼저 모든 월의 1일이용권 비용 vs 1달이용권 비용 비교 후 요금 결정
 * - 3달 이용권
 *   (123) (456) (789) (101112)
 *   1 <= i <= 10 && 3간격.
 *   전부 탐색해야함. 
 * - 마지막으로 1년 이용권과 현재 요금 비교
 * 
 * 시간 복잡도 
 * 상수 시간. 빠름.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int[] prices = new int[4];
			int[] months = new int[13];
			int sum = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				months[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= 12; i++) { // 1일 비용 vs 1달 비용
				months[i] = Math.min(months[i] * prices[0], prices[1]);
				sum += months[i];
			}

			ans = Integer.MAX_VALUE;

			dfs(months, 1, sum, prices[2]);

			// 이건 greedy임.
//			int ans = Integer.MAX_VALUE;
//			for (int i = 1; i <= 10; i++) {
//				int p = i;
//				int localSum = sum;
//				while (p <= 10) {
//					int tempSum = months[p] + months[p + 1] + months[p + 2];
//					if (tempSum > prices[2]) {
//						localSum = localSum - tempSum + prices[2];
//						p += 3;
//					} else {
//						p++;
//					}
//				}
//				ans = Math.min(ans, localSum);
//			}

			ans = Math.min(ans, prices[3]);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}

	public static void dfs(int[] months, int p, int sum, int threePrice) {
		if (p > 10) {
			ans = Math.min(ans, sum);
		}

		if (p <= 10) {
			dfs(months, p + 1, sum, threePrice);
			int tempSum = months[p] + months[p + 1] + months[p + 2];
			if (tempSum > threePrice) {
				sum = sum - tempSum + threePrice;
				dfs(months, p + 3, sum, threePrice);
			}
		}
	}

}
