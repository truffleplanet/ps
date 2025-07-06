import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		for (int i = 1; i <= N; i ++) {
			StringBuilder sb = new StringBuilder();
			int blank = N - i;
			for (int k = 0; k < blank; k++) {
				sb.append(" ");
			}
			for (int j = 0; j < i; j++) {
				sb.append("*");
			}
		System.out.println(sb.toString());
		}
	}
}
