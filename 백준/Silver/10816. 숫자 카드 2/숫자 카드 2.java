import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int min = -10000000;
		final int max = 10000000;
		final int capacity = max - min + 1;
		int[] tempHash = new int[capacity];

		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			tempHash[sc.nextInt() - min] += 1;
		}

		int M = sc.nextInt();
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < M; i++) {
			output.append(tempHash[sc.nextInt() - min]);
			output.append(" ");
		}
		sc.close();
		System.out.println(output.toString().trim());
	}
}
