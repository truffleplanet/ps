import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		long f = 0;
		long s = 1;

		for (int i = 2; i <= N; i++) {
			long t = s;
			s += f;
			f = t;
		}

		System.out.println(s);

	}

}