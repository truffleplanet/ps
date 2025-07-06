import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] heights = new int[9];
		for (int i = 0; i < 9; i++) {
			heights[i] = Integer.parseInt(sc.nextLine());
		}
		sc.close();
		Arrays.sort(heights);
		
		int sum = 0;
		for (int h : heights)
			sum += h;
		int exclude1 = 0, exclude2 = 0;
		
		outer:
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if ((heights[i] + heights[j]) == (sum - 100)) {
					exclude1 = i;
					exclude2 = j;
					break outer;
				}
			}
		}
		
//		System.out.println(heights[i1]);
//		System.out.println(heights[i2]);
		
		for (int i = 0; i < 9; i++) {
			if (i == exclude1 || i == exclude2) {
				continue;
			}
			System.out.println(heights[i]);
		}
		
	}
}
