/*
 * 오버플로우는 항상 조심하자
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] lengths = new int[K];
		for (int i = 0; i < K; i++) {
			lengths[i] = Integer.parseInt(br.readLine());
		}

		long hi = Integer.MAX_VALUE;
		long lo = 1;
		long ans = 1;
		// [lo, hi]
		while (lo <= hi) {
			long mid = (lo + hi) >>> 1;
			if (cut(lengths, mid, N)) {
				ans = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		System.out.println(ans);
	}

	public static boolean cut(int[] arr, long h, int need) {
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i] / h;
			if (sum >= need)
				return true;
		}
		return false;
	}
}
