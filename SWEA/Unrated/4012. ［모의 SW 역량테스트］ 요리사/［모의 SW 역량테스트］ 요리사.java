/*
 * 20000 * 16 * 16은 int범위 내에 포함됨.
 * 
 * 구하려는 것은 차의 최솟값이기 때문에 a, b에는 순서가 없음 
 * 따라서 2/n 개 뽑는 조합을 하되
 * a음식은 0번째 재료를 반드시 포함하도록 한다.  
 * 이렇게 하면 1 2 3 / 4 5 6  과 4 5 6 / 1 2 3 같은 반복을 할 일이 없어짐.
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
			// 입력
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[] isSelected = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			isSelected[0] = 1; // 고정.
			int half = N / 2;
			for (int i = N - 1; i > N - half; i--) { // half - 1개를 뒤에서 선택
				isSelected[i] = 1;
			}

			int minDiff = Integer.MAX_VALUE;

			do {
				int sumA = 0;
				int sumB = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (isSelected[i] == 1 && isSelected[j] == 1) {
							sumA += map[i][j];
							continue;
						}
						if (isSelected[i] == 0 && isSelected[j] == 0) {
							sumB += map[i][j];
							continue;
						}
					}
				}
				int diff = Math.abs(sumA - sumB);
				minDiff = Math.min(minDiff, diff);
				if (minDiff == 0) {
					break;
				}

			} while (np(isSelected));

			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");

		}

		System.out.println(sb);

	}

	public static boolean np(int[] seq) {
		int n = seq.length;

		int i = n - 1;
		while (i > 0 && seq[i - 1] >= seq[i])
			i--;

		if (i == 0)
			return false;

		int j = n - 1;
		while (seq[i - 1] >= seq[j])
			j--;

		swap(seq, i - 1, j);

		int r = n - 1;
		while (i < r) {
			swap(seq, i++, r--);
		}

		return true;
	}

	public static void swap(int[] seq, int i, int j) {
		int tmp = seq[i];
		seq[i] = seq[j];
		seq[j] = tmp;
	}

}
