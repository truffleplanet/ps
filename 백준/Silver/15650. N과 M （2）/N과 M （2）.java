import java.util.Scanner;

public class Main {

	static int N, M, numbers[];
	static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.close();

		numbers = new int[M];
		sb = new StringBuilder();

		backtrack(0, 0);
		System.out.println(sb);

	}

	public static void backtrack(int cnt, int x) {

		if (cnt == M) {
			for (int n : numbers) {
				sb.append(n + " ");
			}
			sb.append('\n');
			return;
		}

		for (int i = x + 1; i <= N; i++) {

			numbers[cnt] = i;
			backtrack(cnt + 1, i);
		}
	}
}
