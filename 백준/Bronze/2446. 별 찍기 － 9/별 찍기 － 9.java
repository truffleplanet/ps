import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		int row = N * 2 - 1;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			if (i < N) {
				for (int j = 0; j < i; j++) {
					sb.append(" ");
				}
				for (int j = 0; j < row - (2 * i); j++) {
					sb.append("*");
				}
			} else {
				for (int j = 0; j < row - i - 1; j++) {
					sb.append(" ");
				}
				for (int j = 0; j < (i - N + 1) * 2 + 1; j++) {
					sb.append("*");
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
