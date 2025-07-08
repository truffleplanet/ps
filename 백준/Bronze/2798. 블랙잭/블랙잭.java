import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] tokens = sc.nextLine().split(" ");
		int n = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);

		String[] line = sc.nextLine().split(" ");
		int[] arr = new int[line.length];
		for (int i = 0; i < line.length; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}
		Arrays.sort(arr);

		int ans = 0;

		for (int i = 0; i < n - 2; i++) {
			if (arr[i] > m)
				break;
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					if ((arr[i] + arr[j] + arr[k]) > m) {
						continue;
					}
					ans = Math.max(ans, arr[i] + arr[j] + arr[k]);
				}
			}
		}
		System.out.println(ans);
	}
}
