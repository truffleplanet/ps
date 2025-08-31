import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 구하려는 것 -> 절단기 높이의 최대값. 
 * 
 * 다르게 말하면, 나무를 가장 적게 가져가는 절단기 높이 중 최대값 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] heights = new int[N];
		int hi = 0;
		int lo = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			if (heights[i] > hi) {
				hi = heights[i];
			}
		}

		// [lo, hi)
		long best = 0;
		while (lo < hi) { // exclusive
			int mid = (lo + hi) / 2;

			long sum = 0;
			for (int i = 0; i < N; i++) {
				if (heights[i] > mid) {
					sum += heights[i] - mid;
				}
			}

			if (sum >= M) {
				best = Math.max(best, mid);
				lo = mid + 1; // 더 우측 탐색 해보기
			} else {
				hi = mid; // exclusive
			}
		}

		System.out.println(best);

	}

}
