import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 전략 - 이분 탐색을 할 것인데, 순회에 드는 비용이 O(N)이므로 
 * 총 시간복잡도는 N log (N)이다. 
 * 
 * 가지치기를 위해 나무 높이를 내림차순으로 정렬해두고, 앞에서부터 순회하며, 나무 길이가 충족했을 시 
 * 바로 순회를 중단하고 높이를 높일 수 있도록 한다. 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		System.out.println(Integer.MAX_VALUE > 2_000_000_000);

		// 입력
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] heights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		Arrays.sort(heights);

		int lo = 0;
		int hi = heights[N - 1]; // M은 최소 1
		while (lo < hi) {
			int mid = lo + ((hi - lo) >>> 1);
			if (cutTree(heights, mid, M)) {
				lo = mid + 1; // 더 잘라도 되나 보러 가기.
			} else
				hi = mid;
		}

		System.out.println(lo - 1); // lo의 최종 갱신 전 상태가 가능했던 최대 높이임.
	}

	// 절단기 높이, 목재 필요 양
	public static boolean cutTree(int[] arr, int h, int need) {
		long sum = 0L;
		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] <= h) // 자를게 없으면
				break;

			sum += arr[i] - h;
			if (sum >= need)
				return true;
		}
		return false;
	}

}
