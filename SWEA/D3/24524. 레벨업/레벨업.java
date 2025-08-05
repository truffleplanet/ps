import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] tokens = br.readLine().split(" ");
			int[] checkpoints = new int[N];
			for (int i = 0; i < N; i++) {
				checkpoints[i] = Integer.parseInt(tokens[i]);
			}

			int[] dists = new int[N - 1];
			int distSum = 0;
			// d_(i) = a_(i+1) - a_(i)
			for (int i = 0; i < N - 1; i++) {
				dists[i] = Math.abs(checkpoints[i + 1] - checkpoints[i]);
				distSum += dists[i];
			}

			int minSum = Integer.MAX_VALUE;
			// 체크포인트 하나씩 빼면서, 계산할 것임

			// case a_0, a_n// 문제 조건 상 반드시 첫과 끝을 가야함.
			//			minSum = Math.min(minSum, distSum - dists[0]);
			//			minSum = Math.min(minSum, distSum - dists[N - 2]);

			// average case
			// i번째 check포인트가 제외되었을 때,
			// d_i-1. = a_(i+1) - a_(i-1)
			for (int i = 1; i < N - 1; i++) {
				int newDist = Math.abs(checkpoints[i + 1] - checkpoints[i - 1]);
				minSum = Math.min(minSum, distSum - dists[i - 1] - dists[i] + newDist);
			}
			System.out.println(minSum);
		}
	}

}
