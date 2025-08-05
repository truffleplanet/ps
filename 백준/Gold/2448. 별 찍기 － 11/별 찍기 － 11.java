import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		for (String s : drawStar(N)) {
			System.out.println(s);
		}
	}

	public static String[] drawStar(int n) {
		if (n == 3) {
			String[] base = { "  *  ", " * * ", "*****" };
			return base;
		}

		int half = n / 2;
		String[] piece = drawStar(half);

		String[] bottom = new String[piece.length];

		for (int i = 0; i < piece.length; i++) {
			bottom[i] = piece[i] + " " + piece[i];
		}

		String[] output = new String[piece.length * 2];
		for (int i = 0; i < piece.length; i++) {
			output[i] = " ".repeat(half) + piece[i] + " ".repeat(half);
		}
		for (int i = piece.length, idx = 0; i < 2 * piece.length; i++, idx++) {
			output[i] = bottom[idx];
		}

		return output;

	}

}
