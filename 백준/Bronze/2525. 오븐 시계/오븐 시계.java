import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();

		B += C;
		while (B > 59) {
			B -= 60;
			A += 1;
		}
		while (A > 23) {
			A -= 24;
		}
		System.out.printf("%d %d", A, B);
	}

}
