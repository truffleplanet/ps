import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();

			int top = N + M;
			int[] count = new int[top + 1]; // 값의 범위는 1 ~ N+M일 것이므로 기록할 배열 선언
			for (int i = 1; i <= N; i++) { // 카드 세트 1
				for (int j = 1; j <= M; j++) { // 카드 세트 2
					count[i + j]++;
				}
			}

			int maxCnt = 0;
			for (int i = 0; i < count.length; i++) { // 최빈값 찾기
				maxCnt = Math.max(maxCnt, count[i]);
			}

			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < count.length; i++) { // 최빈값 전부 출력
				if (count[i] == maxCnt) {
					sb.append(i).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
