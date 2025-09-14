import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] seq = new int[N];

		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> LIS = new ArrayList<>();
		LIS.add(seq[0]);

		for (int i = 1; i < N; i++) {
			if (LIS.get(LIS.size() - 1) < seq[i]) {
				LIS.add(seq[i]);
			} else {
				int idx = Collections.binarySearch(LIS, seq[i]);
				if (idx < 0) {
					idx = -idx - 1;
				}
				LIS.set(idx, seq[i]);
			}
		}

		System.out.println(LIS.size());
	}

}
