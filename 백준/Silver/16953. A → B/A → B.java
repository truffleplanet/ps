import java.util.Scanner;

public class Main {
	static long target, ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextInt();
		target = sc.nextInt();
		ans = Integer.MAX_VALUE;
		sc.close();

		dfs(a, 0);
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans + 1);
		}
	}

	public static void dfs(long x, int depth) {
		if (x > target) {
			return;
		}
		if (x == target) {
			ans = Math.min(ans, depth);
			return;
		}

		dfs(x * 2, depth + 1);
		dfs(x * 10 + 1, depth + 1);

	}
}
