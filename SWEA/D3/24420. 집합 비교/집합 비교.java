import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for (int t = 0; t < T; t++) {
			sc.nextLine();
			String[] tokensA = sc.nextLine().split(" ");
			String[] tokensB = sc.nextLine().split(" ");

			long[] bitsetA = new long[2];
			long[] bitsetB = new long[2];

			for (String c : tokensA) {
				int n = Integer.parseInt(c);
				bitsetA[n / 64] |= (1L << (n % 64));
			}

			for (String c : tokensB) {
				int n = Integer.parseInt(c);
				bitsetB[n / 64] |= (1L << (n % 64));
			}

			boolean equals = true;
			boolean subsetOfA = true;
			boolean subsetOfB = true;

			for (int i = 0; i < 2; i++) {
				if ((bitsetA[i]) != bitsetB[i]) {
					equals = false;
					break;
				}
			}

			if (equals) {
				System.out.println("=");
				continue;
			}

			for (int i = 0; i < 2; i++) {
				if ((bitsetB[i] & ~bitsetA[i]) != 0) {
					subsetOfA = false;
					break;
				}
			}

			if (subsetOfA) {
				System.out.println(">");
				continue;
			}

			for (int i = 0; i < 2; i++) {
				if ((bitsetA[i] & ~bitsetB[i]) != 0) {
					subsetOfB = false;
					break;
				}
			}

			if (subsetOfB) {
				System.out.println("<");
				continue;
			}

			System.out.println("?");

		}
	}
}
