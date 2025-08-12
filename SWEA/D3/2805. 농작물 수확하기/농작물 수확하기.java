import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String n = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = n.charAt(j) - '0';
				}
			}

			int sum = 0; // 마름모를 순회하며 합을 저장할 변수
			int middle = N / 2; // 시작 위치이자, 마름모의 단계가 될 값

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (Math.abs(i - middle) + Math.abs(j - middle) <= middle) // 맨해튼 거리가 마름모 단계보다 작거나 같으면
						sum += map[i][j];
				}
			}

			sb.append("#").append(t).append(" ").append(sum).append("\n"); // 출력할 결과 담아두

		} // 모든 테스트 케이스 종료
		System.out.println(sb); // 출력
	}

}
