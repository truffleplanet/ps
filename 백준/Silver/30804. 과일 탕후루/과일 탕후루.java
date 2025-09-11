
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] huru = new int[N];
		for (int i = 0; i < N; i++) {
			huru[i] = Integer.parseInt(st.nextToken());
		}

		int[] cnt = new int[10];
		int kinds = 0;
		int ans = 0;
		for (int L = 0, R = 0; R < N; R++) {
			int x = huru[R];

			if (cnt[x] == 0)
				kinds++;
			cnt[x]++;

			while (kinds > 2) {
				int y = huru[L++];
				cnt[y]--;
				if (cnt[y] == 0)
					kinds--;
			}

			ans = Math.max(ans, R - L + 1);
		}

		System.out.println(ans);
	}

}
