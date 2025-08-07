import java.util.Scanner;

public class Main {

	static int N, M, numbers[];
	static boolean isSelected[];
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.close();

		numbers = new int[M];
		isSelected = new boolean[N + 1];
		sb = new StringBuilder();

		backtrack(0);
		System.out.println(sb);

	}

	public static void backtrack(int cnt) {

		if (cnt == M) {
			for (int n : numbers) {
				sb.append(n + " ");
			}
			sb.append('\n');
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) {
				continue;
			}
			numbers[cnt] = i;
			isSelected[i] = true;
			backtrack(cnt + 1);
			isSelected[i] = false;
		}
	}
}
