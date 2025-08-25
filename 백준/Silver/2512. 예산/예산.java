import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static long cutoffSum(int[] arr, int cutoff) {
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += Math.min(arr[i], cutoff);
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());

		long sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}

		Arrays.sort(arr);

		int B = Integer.parseInt(br.readLine());

		if (sum <= B) {
			System.out.println(arr[N - 1]);
			return;
		}

		int hi = arr[N - 1];
		int lo = B / N;

		int maxB = 0;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >>> 1);
			long cutoffSum = cutoffSum(arr, mid);

			if (cutoffSum <= B) {
				maxB = mid;
				lo = mid + 1;
			} else
				hi = mid - 1;
		}

		System.out.println(maxB);

	}
}
