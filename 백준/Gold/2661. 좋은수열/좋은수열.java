import java.util.Scanner;

public class Main {

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();

		backtrack(new StringBuilder());
	}

	public static boolean backtrack(StringBuilder k) {
		if (k.length() == N) {
			System.out.println(k.toString());
			return true;
		}

		for (int i = 1; i <= 3; i++) {
			k.append(i);
			if (isGood(k)) {
				if (backtrack(k))
					return true;
			}
			k.deleteCharAt(k.length() - 1);
		}
		return false;
	}

	public static boolean isGood(StringBuilder k) {
		int K = k.length();
		int R = K / 2;

		for (int s = 1; s <= R; s++) {
			boolean isMatch = true;
			int i = K - 1;
			int j = i - s;
			for (int c = 0; c < s; c++) {
				if (k.charAt(i) != k.charAt(j)) {
					isMatch = false;
					break;
				}
				i--;
				j--;
			}
			if (isMatch) {
				return false;
			}
		}
		return true;
	}
}