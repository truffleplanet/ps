
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

		int[] belt = new int[N];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}

		int[] sushiCount = new int[D + 1];

		int maxKinds = 0;
		int currentKinds = 1;
		for (int i = 0; i < K; i++) {
			currentKinds = addSushi(belt[i], sushiCount, C, currentKinds);
		}

		maxKinds = currentKinds;

		if (maxKinds == K + 1) {
			System.out.println(maxKinds);
			return;
		}

		for (int i = K; i < N + K; i++) {
			int prev = belt[i - K];
			int next = belt[i % N];

			currentKinds = removeSushi(prev, sushiCount, C, currentKinds);
			currentKinds = addSushi(next, sushiCount, C, currentKinds);

			maxKinds = Math.max(maxKinds, currentKinds);

			if (maxKinds == K + 1) {
				System.out.println(maxKinds);
				return;
			}
		}

		System.out.println(maxKinds);
	}

	private static int addSushi(int sushi, int[] sushiCount, int coupon, int currentKinds) {
		if (sushi == coupon)
			return currentKinds;
		if (sushiCount[sushi] == 0)
			currentKinds++;
		sushiCount[sushi]++;
		return currentKinds;
	}

	private static int removeSushi(int sushi, int[] sushiCount, int coupon, int currentKinds) {
		if (sushi == coupon)
			return currentKinds;
		if (sushiCount[sushi] == 1)
			currentKinds--;
		sushiCount[sushi]--;
		return currentKinds;
	}
}