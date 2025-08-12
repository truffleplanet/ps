import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

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

			// 재귀적으로 생각해보면 마름모 크기가 한 단계 커진다는 것은
			// 이전 마름모의 각 점에서 상하좌우 인접 점을 색칠하는 것과 같다.
			// 1x1마름모를 0단계, 3x3 마름모를 1단계 ... NxN 마름모를 (N / 2) 단계라고 정의해보자.
			// 따라서 0단계에서 시작하여, 델타탐색으로 다음 점들을 큐에 넣되,
			// 목표 단계까지만 탐색해야 하니, 단계 정보를 점의 좌표와 함께 관리해야 한다.
			// 그러니 큐에 저장하는 점은, int[] {r, c, phase}로 좌표와 단계를 함께 표현한다.

			int sum = 0; // 마름모를 순회하며 합을 저장할 변수
			boolean[][] visited = new boolean[N][N]; // 방문한 점을 표시할 불리언 배열
			int middle = N / 2; // 시작 위치이자, 마름모의 단계가 될 값

			Queue<int[]> queue = new ArrayDeque<>(); // Queue인터페이스로, 구현체를 ArrayDeque로.

			queue.offer(new int[] { middle, middle, 0 }); // 시작 점을 큐에 넣습니다
			visited[middle][middle] = true; // 시작점을 방문처리 합니다.
			sum += map[middle][middle]; // 시작점 값을 합에 더해줍니다.

			while (!queue.isEmpty()) { // 큐가 비어있지 않다면
				int[] node = queue.poll(); // 큐에 있는 점을 꺼냅니다.
				int r = node[0];
				int c = node[1];
				int phase = node[2];

				if (phase == middle) { // 일종의 base case - 현재 단계가 최대 단계이면 그만합니다.
					continue;
				}
				// 현재 점에서 갈 수 있는 상하좌우 점을 탐색합니다.
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!visited[nr][nc]) { // 탐색한 점이 방문한 적이 없는 점이라면
						visited[nr][nc] = true; // 방문 처리 하고
						sum += map[nr][nc]; // 해당 위치의 값을 더하고
						queue.offer(new int[] { nr, nc, phase + 1 }); // 큐에 던져줍니다. 단계 +1 하고!
					}
				}
			} // queue가 비어(탐색이 끝나) while문이 종료되었습니다.

			sb.append("#").append(t).append(" ").append(sum).append("\n"); // 출력할 결과 담아두

		} // 모든 테스트 케이스 종료
		System.out.println(sb); // 출력
	}

}
