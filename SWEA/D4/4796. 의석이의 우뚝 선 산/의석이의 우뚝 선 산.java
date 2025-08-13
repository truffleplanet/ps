/*
 * 문제 조건을 생각해보자
 * 1. nested 우뚝 선 산이 가능한가?
 * - 가능하지 않지만, 한 우뚝 선 산 내에 여러 우뚝 선 산 구간이 생긴다. 
 * [1 4 6 5 3 7 9 2 8]
 * 실제 우뚝 선 산 [1 4 (6) 5 3], [3, 7, (9), 2] 
 * -> left, right 개수 기록해두고 left * right하면 전체 갯수 됨.
 * (1 4 6 5), (1, 4, 6, 5, 3), 
 * 	  (4 6 5) (4, 6, 5, 3)
 *                  (3 7 9,2)
 *                  (7, 9, 2)
 * 2. [1 2 3 3 2 1]은 우뚝 선 산인가?
 * - 아니다. 크기 조건에 위배됨
 * - 즉 증감을 보며,
 * 오름차순이면, left += 1;
 * 내림차순이 되면 right +=1
 * 아예 같은 수가 나오거나 오름차순이면, 구간을 새로 세기 시작
 * 반복.
 */

import java.io.IOException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
//		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}

			int left = 0;
			int right = 0;
			int sum = 0;
			for (int i = 1; i < N; i++) {
				if (arr[i] == arr[i - 1]) {
					sum += left * right; // 오르는 중이면 right = 0,
					left = 0;
					right = 0;
				} else if (arr[i] > arr[i - 1]) {
					if (right == 0) {
						left++;
					} else {
						sum += left * right;
						left = 1;
						right = 0;
					}
				} else { // 내려갈 때
					right++;
					if (i == N - 1) {
						sum += left * right;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(sum).append("\n");

		}

		System.out.println(sb);

	}

}
