/*
 * 매칭문제가 아니다.
 * 
 * 백터의 합을 구한 후, 가장 작은 백터의 크기를 만드는 문제임.
 * 매칭을 할 때 백터는 더해지는 백터, 빼는 백터로 나뉨.
 * 즉 누구와 매칭하는가는 중요하지 않고 최종 백터는 10개 백터를 합하고, 10개는 빼서 만들면 됨.
 * 
 * 전체에서 10개 뽑는 조합 문제.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] pos;
	static boolean[] selected;
	static long ans;

	static void combination(int start, int depth, int R) {

		if (depth == R) {
			long x = 0;
			long y = 0;
			for (int i = 0; i < N; i++) {
				int vx = pos[i][0];
				int vy = pos[i][1];
				if (selected[i]) {
					x += vx;
					y += vy;
				} else {
					x -= vx;
					y -= vy;
				}
			}
			long s = (long) x * x + y * y;
			ans = Math.min(ans, s);
			return;
		}

		if (N - start < R - depth)
			return;

		for (int i = start; i < N; i++) {
			selected[i] = true;
			combination(i + 1, depth + 1, R);
			selected[i] = false;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			pos = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}

			selected = new boolean[N];
			selected[0] = true;
			ans = Long.MAX_VALUE;
			combination(1, 1, N / 2);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}

		System.out.println(sb);
	}

}
