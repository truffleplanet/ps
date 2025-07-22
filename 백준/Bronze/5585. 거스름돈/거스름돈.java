import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int money = 1000 - sc.nextInt();
		int count = 0;

		count += money / 500;
		money %= 500;

		count += money / 100;
		money %= 100;

		count += money / 50;
		money %= 50;

		count += money / 10;
		money %= 10;

		count += money / 5;
		money %= 5;

		count += money;

		System.out.println(count);
		sc.close();
	}

}
