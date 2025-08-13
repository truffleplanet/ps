import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] opers = new int[N - 1]; // + - * /
			int[] nums = new int[N];

			// 연산자 입력 처리
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			for (int i = 0; i < 4; i++) { // 0+, 1-, 2*, 3/
				int o = Integer.parseInt(st.nextToken());
				for (int k = 0; k < o; k++) {
					opers[idx++] = i;
				}
			}

			// 숫자 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;

			do {
				int cur = nums[0];
				for (int i = 1; i < nums.length; i++) {
					cur = cal(opers[i - 1], cur, nums[i]);
				}
				max = Math.max(max, cur);
				min = Math.min(min, cur);

			} while (nextPermutation(opers));
			// 문제를 잘못 읽음.
//			do {
//				// 각 숫자 순열별 가능한 모든 연산자 조합 확인해보기
//				System.out.println(Arrays.toString(nums));
//				for (List<Integer> op : operP) {
//					int cur = nums[0];
//					for (int i = 1; i < nums.length; i++) {
//						cur = cal(op.get(i - 1), cur, nums[i]);
//					}
//					max = Math.max(max, cur);
//					min = Math.min(min, cur);
//				}
//
//			} while (nextPermutation(nums));

			int ans = Math.abs(max - min);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}

	public static boolean nextPermutation(int[] arr) {
		int N = arr.length;

		int i = N - 1; // 뒤에서부터 오름차순이 깨지는 순간 찾기
		while (i > 0 && arr[i - 1] >= arr[i])
			i--;

		if (i == 0)
			return false; // 없으면 전체가 내림차순이니 사전순의 마지막. 종료

		int j = N - 1;
		while (arr[i - 1] >= arr[j]) { // 교환 위치보다 큰 값중 가장 작은 값 찾기
			j--;
		}

		swap(arr, i - 1, j);

		int end = N - 1;
		while (i < end) { // 부분 정렬
			swap(arr, i++, end--);
		}
		return true;

	}

	public static void swap(int[] arr, int i, int j) {
		if (i == j)
			return;

		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;

	}

	public static int cal(int type, int a, int b) throws Exception {
		switch (type) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			return a / b;
		}
		throw new Exception();
	}

}
