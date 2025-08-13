import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			int[][] stuffs = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int val = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				stuffs[i][0] = val;
				stuffs[i][1] = cal;
			}

			int max = 0;
			int[] p = new int[N];
			for (int i = N - 1; i >= 0; i--) {
				p[i] = 1;
				int[] tp = p.clone();
				do {
					int valSum = 0;
					int calSum = 0;
					for (int k = 0; k < N; k++) {
						if (tp[k] == 0)
							continue;
						if (calSum + stuffs[k][1] > L) {
							break;
						}
						valSum += stuffs[k][0];
						calSum += stuffs[k][1];
					}
					max = Math.max(max, valSum);
				} while (np(tp));
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");

		}

		System.out.println(sb);

	}

	public static boolean np(int[] a) {
		int N = a.length;

		int i = N - 1;
		while (i > 0 && a[i - 1] >= a[i])
			i--;

		if (i == 0)
			return false;

		int j = N - 1;
		while (a[i - 1] >= a[j])
			j--;

		swap(a, i - 1, j);

		int l = i;
		int r = N - 1;
		while (l < r)
			swap(a, l++, r--);

		return true;
	}

	public static void swap(int[] a, int i, int j) {
		if (i == j)
			return;
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;

	}

}
