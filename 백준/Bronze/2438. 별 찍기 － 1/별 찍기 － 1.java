import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder output = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				output.append("*");
			}
			output.append("\n");
		}
		System.out.print(output);
	}
}