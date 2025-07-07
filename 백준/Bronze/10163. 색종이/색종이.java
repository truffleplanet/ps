import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		int[][] plain = new int[1001][1001];
		int[] ans = new int[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			String[] tokens = sc.nextLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			int width = Integer.parseInt(tokens[2]);
			int height = Integer.parseInt(tokens[3]);
			
			for (int n = x; n < x + width; n++) {
				for (int m = y; m < y + height; m++) {
					if (plain[n][m] != i) {
						ans[plain[n][m]]--;
						plain[n][m] = i;
						ans[i]++;
					}
				}
			}
		}
		for (int i = 1; i < N + 1; i++) {
			System.out.println(ans[i]);
		}

	}
}
