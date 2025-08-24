import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		}

		System.out.println(sb);

	}

	public static boolean binarySearch(int[] arr, int key) {
		int hi = arr.length;
		int lo = 0;

		while (lo < hi) {
			int mid = lo + ((hi - lo) >>> 1); // 오버 플로우 방지 & 컨벤션 상 음수가 아니라는 표시
			if (arr[mid] == key)
				return true;
			else if (arr[mid] < key)
				lo = mid + 1;
			else
				hi = mid;
		}

		return false;
	}
}
