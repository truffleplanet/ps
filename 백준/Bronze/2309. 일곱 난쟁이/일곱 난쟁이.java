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
		int i1 = 0, i2 = 0;
		boolean stop = false;
		for (int i = 0; i < 8; i++) {
			for (int j = i; j < 9; j++) {
				if ((heights[i] + heights[j]) == (sum - 100)) {
					stop = true;
					i1 = i;
					i2 = j;
					break;
				}
			}
		if (stop == true) break;
		}
		
//		System.out.println(heights[i1]);
//		System.out.println(heights[i2]);
		
		for (int i = 0; i < 9; i++) {
			if (i == i1 || i == i2) {
				continue;
			}
			System.out.println(heights[i]);
		}
		
	}
}
