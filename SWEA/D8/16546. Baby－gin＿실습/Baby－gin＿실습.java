
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 0~9 사이의 숫자 6장을 뽑았을 때
 * 3장이 연속이면 run,
 * 3장이 동일한 번호면 triplet
 * 
 * run, triplet으로만 이루어져 있으면 baby-gin
 * 6자리 숫자를 입력받아 baby-gin여부 판단하기
 * 
 * 순열로 풀기
 * main idea : 
 * 순열로 가능한 모든 경우를 늘어놓으면
 * 앞 세 장, 뒷 세 장 잘라서 판단해도 된다.
 * 
 * {1, 2, 3, 2, 3, 2}
 *  
 * 카운팅으로 풀기
 * 
 * 
 * 
 */

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			char[] cards = br.readLine().toCharArray();
			boolean ans = false;

			// for문으로 순열 만들기
			outerLoop: for (int i1 = 0; i1 < cards.length; i1++) {
				for (int i2 = 0; i2 < cards.length; i2++) {
					if (i1 != i2) {
						for (int i3 = 0; i3 < cards.length; i3++) {
							if (i2 != i3 && i3 != i1) {
								for (int i4 = 0; i4 < cards.length; i4++) {
									if (i3 != i4 && i4 != i1 && i4 != i2) {
										for (int i5 = 0; i5 < cards.length; i5++) {
											if (i4 != i5 && i5 != i1 && i5 != i2 && i5 != i3) {
												for (int i6 = 0; i6 < cards.length; i6++) {
													if (i5 != i6 && i6 != i1 && i6 != i2 && i6 != i3 && i6 != i4) {
														ans = (isRun(cards[i1], cards[i2], cards[i3])
																|| isTriplet(cards[i1], cards[i2], cards[i3]))
																&& (isRun(cards[i4], cards[i5], cards[i6])
																		|| isTriplet(cards[i4], cards[i5], cards[i6]));
														if (ans)
															break outerLoop;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			System.out.println("#" + t + " " + ans);

		}

	}

	public static boolean isRun(int a, int b, int c) {
		return (a == b && b == c);
	}

	public static boolean isTriplet(int a, int b, int c) {
		return (b == a + 1 && c == b + 1) || (a == b - 1 && b == c - 1);
	}
}
