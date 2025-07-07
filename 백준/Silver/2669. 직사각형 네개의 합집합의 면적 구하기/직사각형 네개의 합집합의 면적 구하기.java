import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] plain = new int[101][101];
		int ans = 0;
		
		for (int i = 0; i < 4; i++) {
			String[] tokens = sc.nextLine().split(" ");
			int x1 = Integer.parseInt(tokens[0]);
			int x2 = Integer.parseInt(tokens[2]);
			int y1 = Integer.parseInt(tokens[1]);
			int y2 = Integer.parseInt(tokens[3]);
			for (int n = y1; n < y2; n++) {
				for (int m = x1; m < x2; m++) {
					if (plain[n][m] == 0) {
						plain[n][m] = 1;
						ans++;
					}
				}
			}
		}
		System.out.println(ans);
		}
	}
