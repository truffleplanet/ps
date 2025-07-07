import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] plain = new int[101][101];
		int ans = 0;
		
		for (int i = 0; i < 4; i++) {
			String[] tokens = sc.nextLine().split(" ");
			for (int n = Integer.parseInt(tokens[1]); n < Integer.parseInt(tokens[3]); n++) {
				for (int m = Integer.parseInt(tokens[0]); m < Integer.parseInt(tokens[2]); m++) {
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
