
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] pr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				pr[i] = Integer.parseInt(st.nextToken());
			}

			ArrayList<Integer>[] pos = new ArrayList[10];
			for (int p = 0; p <= 9; p++) {
				pos[p] = new ArrayList<>();
			}
			for (int i = 0; i < N; i++) {
				pos[pr[i]].add(i);
			}

			int head = 0;
			int targetIdx = M;
			int targetPr = pr[targetIdx];
			int count = 0;

			Outer: for (int p = 9; p >= targetPr; p--) {
				if (pos[p].isEmpty())
					continue;

				int x = Collections.binarySearch(pos[p], head);
				if (x < 0) {
					x = -x - 1;
				}

				int start = x;

				for (int r = start; r < pos[p].size(); r++) {
					int idx = pos[p].get(r);
					count++;
					head = (idx + 1) % N;
					if (idx == targetIdx) {
						sb.append(count).append('\n');
						break Outer;
					}
				}

				for (int l = 0; l < start; l++) {
					int idx = pos[p].get(l);
					count++;
					head = (idx + 1) % N;
					if (idx == targetIdx) {
						sb.append(count).append('\n');
						break Outer;
					}
				}

			}
		}

		System.out.println(sb);
	}
}
