import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		int[] cnt = new int[100_001];
		int ans = 0;
		for (int L = 0, R = 0; R < N; R++) {
			int x = seq[R];
			cnt[x]++;

			while (cnt[x] > K) {
				int y = seq[L++];
				cnt[y]--;
			}

			ans = Math.max(ans, R - L + 1);
		}

		System.out.println(ans);
	}
}
