import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 여러 방법이 있겠지만 
 * 정렬 후 이분 탐색을 연습해보자. 
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 입력 1
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬
		Arrays.sort(nums);

		// 입력 2
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int key = Integer.parseInt(st.nextToken());
			if (binarySearch(nums, key))
				sb.append(1);
			else
				sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	// 이분탐색
	public static boolean binarySearch(int[] arr, int key) {
		int hi = arr.length;
		int lo = 0;

		// [lo hi) 일 때와 [lo, hi]일 때의 차이는?
		while (lo < hi) {
			int mid = (hi + lo) / 2;
			if (arr[mid] == key) {
				return true;
			} else if (arr[mid] > key) {
				hi = mid; // 반열림이면, hi는 미포함으로 유지.
			} else {
				lo = mid + 1;
			}

		}
		return false;
	}

}
