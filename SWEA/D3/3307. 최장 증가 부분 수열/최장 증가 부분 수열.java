import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] seq = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}

			List<Integer> LIS = new ArrayList<>();
			LIS.add(Integer.MIN_VALUE);

			for (int i = 0; i < N; i++) {
				if (LIS.get(LIS.size() - 1) < seq[i]) {
					LIS.add(seq[i]);
				} else {
					int idx = Collections.binarySearch(LIS, seq[i]);
					idx = (idx < 0) ? (-idx - 1) : idx;
					LIS.set(idx, seq[i]);
				}
			}

			sb.append('#').append(tc).append(' ').append(LIS.size() - 1).append('\n');

		}

		System.out.println(sb);
	}

}
