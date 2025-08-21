import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		
		int[] cables = new int[K];
		long maxLen = 0;
		for (int i =0; i < K; i++) {
			cables[i] = Integer.parseInt(br.readLine());
			maxLen = Math.max(maxLen, cables[i]);
		}

		long right = Integer.MAX_VALUE;
		long left = 0;
		long best = 1;

		while (left <= right) {
			long mid = (right + left) / 2;
			long cnt = 0;
			for (int c : cables) {
				cnt += c / mid;
			}

			if (cnt >= N) {
				best = Math.max(mid, best);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(best);

	}
}
