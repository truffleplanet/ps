import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] seq;
	static boolean[] selected;
	static int N;
	static int S;
	static int cnt;

	static void subset(int idx, int size) {

		if (idx == N) {
			logic(size);
			return;
		}

		selected[idx] = true;
		subset(idx + 1, size + 1);
		selected[idx] = false;
		subset(idx + 1, size);
	}

	static void logic(int size) {
		if (size == 0)
			return;

		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (selected[i])
				sum += seq[i];
		}
		if (sum == S)
			cnt++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		seq = new int[N];
		selected = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		subset(0, 0);

		System.out.println(cnt);

	}

}
