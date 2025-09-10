
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[] cnt = new int[D + 1];

		int ans = 0;
		int now = 1;
		for (int i = 0; i < K; i++) {
			int x = arr[i];
			if (x == C)
				continue;
			if (cnt[x] == 0) {
				cnt[x]++;
				now++;
			} else {
				cnt[x]++;
			}
		}

		ans = now;

		if (ans == K + 1)

		{
			System.out.println(ans);
			return;
		}

		for (int i = K; i < N + K; i++) {
			int prev = arr[i - K];
			int nx = (i >= N) ? (i % N) : i;
			int next = arr[nx];

			if (prev == C) {
				;
			} else if (cnt[prev] == 1) {
				cnt[prev]--;
				now--;
			} else {
				cnt[prev]--;
			}

			if (next == C) {
				;
			} else if (cnt[next] == 0) {
				cnt[next]++;
				now++;
			} else {
				cnt[next]++;
			}

			ans = Math.max(ans, now);

			if (ans == K + 1) {
				System.out.println(ans);
				return;
			}
		}

		System.out.println(ans);
	}
}