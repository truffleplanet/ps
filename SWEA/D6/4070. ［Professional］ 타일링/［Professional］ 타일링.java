/*	-
 *  -  ㅁ |
 *  | 는 아무때나 뒤에 붙일 수 있고
 *  눕힌 두칸, 태생 두칸은 짝수마다 붙일 수 있음. 
 *  두번째 전 것에 = ㅁ 붙이기 +  바로 전 것에 | 붙이기.
 *  끝.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		BigInteger[] dp = new BigInteger[251];
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");

		BigInteger two = new BigInteger("2");

		for (int i = 2; i < 251; i++) {
			dp[i] = dp[i - 2].multiply(two).add(dp[i - 1]);
		}

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append('#').append(tc).append(' ').append(dp[N].toString()).append('\n');
		}
		System.out.println(sb);
	}

}
