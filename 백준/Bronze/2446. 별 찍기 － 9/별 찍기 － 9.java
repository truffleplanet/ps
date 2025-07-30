import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int N = sc.nextInt();
		sc.close();
		
		for (int i = 0; i < N; i++) {
			for (int q = 0; q < i; q++) {
				System.out.print(" ");
			}
			for (int p = 0; p < 2 * N - 2 * i - 1; p++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for (int i = 0; i < N - 1; i++) {
			for (int q = 0; q < N - i - 2; q++) {
				System.out.print(" ");
			}
			for (int p = 0; p < 3 + (2 * i); p++) {
				System.out.print("*");
			}
			System.out.println();
		}		
		
	}
}