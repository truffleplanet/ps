/*
	X -> [0, N - K], Y -> [K, M]
	Xs -> x, x - k,
	Ys -> y, y + k
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] stones = new int[T][2];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			stones[i][0] = Integer.parseInt(st.nextToken());
			stones[i][1] = Integer.parseInt(st.nextToken());
		}

		HashSet<Integer> xCandidates = new HashSet<>();
		HashSet<Integer> yCandidates = new HashSet<>();
		xCandidates.add(0);
		xCandidates.add(W - K);
		yCandidates.add(K);
		yCandidates.add(H);

		for (int[] stone : stones) {
			int x1 = stone[0];
			int x2 = x1 - K;
			int y1 = stone[1];
			int y2 = y1 + K;

			if (x1 <= W - K) xCandidates.add(x1);
			if (x2 >= 0 && x2 <= W - K)  xCandidates.add(x2);
			if (y1 >= K) yCandidates.add(y1);
			if (y2 <= H && y2 >= K)  yCandidates.add(y2);
		}

		int best = 0;
		int bestX = 0;
		int bestY = 0;

		for (int x : xCandidates) {
			for (int y : yCandidates) {
				int count = 0;
				for (int[] stone : stones) {
					if (stone[0] >= x && stone[0] <= x + K && stone[1] <= y && stone[1] >= y - K) count++;
				}
				if (count > best) {
					best = count;
					bestX = x;
					bestY = y;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(bestX).append(' ').append(bestY);
		sb.append('\n');
		sb.append(best);
		System.out.println(sb.toString());
	}
}
