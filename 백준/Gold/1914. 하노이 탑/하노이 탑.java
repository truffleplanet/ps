import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		System.out.println(BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE));
		hanoi(n, "1", "3", "2");
	}

	public static void move(String startPeg, String targetPeg) {
		System.out.println(startPeg + " " + targetPeg);
	}

	public static void hanoi(int n, String startPeg, String targetPeg, String tempPeg) {
		if (n > 20) {
			return;
		}

		if (n > 0) {
			hanoi(n - 1, startPeg, tempPeg, targetPeg);
			move(startPeg, targetPeg);
			hanoi(n - 1, tempPeg, targetPeg, startPeg);
		}
	}

}
