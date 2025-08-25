import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Brute Force
 * 그냥 조합문제로 바꿔서 완전 탐색하기. 
 * n이 20만이고 r은 0..n이므로 불가능.
 * 
 * 
 * solution
 * 이분탐색으로 해결한다.
 * 
 * 처음부터 순회하며, 만약에 최소거리 M 이상으로 C개의 공유기 설치가 가능하다면 우측탐색
 * 불가능하다면 좌측탐색. 
 * 
 * 
 */

public class Main {

	public static boolean check(int[] map, int m, int c) { // 최소거리 m 이상으로 c개의 공유기를 설치할 수 있는가?
		int N = map.length;
		int prev = map[0];
		int cnt = 1; // 맨 앞에 무조건 설치한다고 가정.

		for (int i = 1; i < N; i++) {
			int cur = map[i];
			if (cur - prev >= m) {
				cnt++;
				prev = cur;
			}

			if (cnt >= c)
				return true;
		}

		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] map = new int[N];
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(map);

		int hi = map[N - 1] - map[0];
		int lo = 1;
		int ans = 0;
		// [lo, hi]
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >>> 1);
			if (check(map, mid, C)) {
				ans = mid;
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		System.out.println(ans);
	}

}
