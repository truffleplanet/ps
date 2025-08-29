/*
 * 각 열, 행은 독립.
 * 
 * 따라서 어떤 정수 수열과 경사로 길이 k가 들어왔을 때, 가능한지 여부를 판단하는 프로그램만 작성하면 됨. 
 * -- 한 수열에서 2개 이상의 경우가 나올 수는 없음(정확히 증명은 못하겠으나 가능한 케이스를 못 찾겠음)
 * -- 직관적으로는 모든 높은 곳 좌우로 낮은 곳과 전부 연결해야 하니깐.. 분기가 나올 일이 없는 듯.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static boolean canBuild(int[] seq, int k) {
		int N = seq.length;
		boolean[] built = new boolean[N];
		int prev = seq[0];
		for (int i = 1; i < N; i++) {
			if (seq[i] == prev) // 같거나
				continue;

			if (seq[i] == prev + 1) { // 한칸 높을 때
				// 뒤 쪽에 경사로 세울 수 있는 지 확인
				if (i - k < 0)
					return false;

				for (int l = i - 1; l >= i - k; l--) {
					if (built[l] || seq[l] != prev) {
						return false;
					}
					built[l] = true;
				}

			} else if (seq[i] == prev - 1) { // 한칸 낮을 때
				// 앞 쪽에 경사로 세울 수 있는지 확인
				if (i + k > N)
					return false;
				for (int r = i; r < i + k; r++) {
					if (built[r] || seq[r] != seq[i]) {
						return false;
					}
					built[r] = true;
				}
			} else { // 2 이상 차이가 나는 경우
				return false;
			}
			// prev 갱신
			prev = seq[i];
		}

		return true; // 모든 과정을 무사히 통과하면 활주로 건설 가능이다... 제발..

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[] seq = new int[N];
			int ans = 0;

			// 행 입력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					seq[j] = map[i][j];
				}
				if (canBuild(seq, K)) {
					ans++;
				}
			}

			// 열 입력
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					seq[i] = map[i][j];
				}
				if (canBuild(seq, K)) {
					ans++;
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");

		}

		System.out.println(sb);

	}
}
