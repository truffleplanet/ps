import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 정의:
 * 지도에 주어지는 것은 비이다.
 * 점 s에서 점 g까지 최소 비용이 드는 경로를 구하여라 
 * 
 * 제약:
 * 이동은 상하좌우로 한 칸씩 가능하다.
 * 출발지 S는 항상 (0,0), 도착지 G는 항상 (N-1, N-1)이다.
 * 지도의 너비, 높이인 N은 최대 100이다.
 * 깊이는 최대 9이다. (정확히 조건이 주어진 것은 아니지만, 입력이 공백없이 주어짐) 
 * 
 * 아이디어: 
 * 먼저 가중치가 다 다르기 때문에, bfs로는 최단 경로 구하기 까다롭다.
 * 혹시 DP가 되지 않을까? 출발지와 도착지가 정해져 있다는 제약조건 생각하면, 
 * 어떤 점 x에 가는 방법은, 아래에서 내려오는 것과 왼쪽에서 오른쪽으로 오는 것..뿐 같은데.
 * 아니다 이 예시를 보면 왼쪽과 위로 움직이는 것도 도움이 될 때가 있다.  
 * 011119999
 * 999919999
 * 999119999
 * 999191111
 * 999111990
 * 
 * 그러면 각 점에 이르는 최단 경로를 기록하는 건 어떨까? 
 * N*N*4
 * 
 * bfs인데 모든 방향으로 
 * 현재 노드보다 목표점 크거나 같으면 갱신 x; 
 * 현재 노드가 작을 때만 갱신
 * 
 * 
 * 다만 첫 방문일 때는 더 클 경우가 많을텐데.. 아 이런식으로 하면 안되네.
 * 이렇게 하면 순회는 결국에 끝나고. 순회가 다 끝난 후 목표 지점에는 최소값이 기록되어있을 것임. 
 * 
 * 
 * 구현순서: 
 * 입력 받은 후
 * bfs..
 *
 */

public class Solution {

	static class Node {
		int r;
		int c;

		private Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		final int[] DR = { 0, 0, 1, -1 };
		final int[] DC = { 1, -1, 0, 0 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			} // 입력 종료

			int[][] sumMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(sumMap[i], -1);
			}
			sumMap[0][0] = 0;

			Queue<Node> q = new ArrayDeque<>();
			q.offer(new Node(0, 0));

			while (!q.isEmpty()) {
				Node u = q.poll();

				for (int t = 0; t < 4; t++) {
					int nr = u.r + DR[t];
					int nc = u.c + DC[t];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;

					int newSum = sumMap[u.r][u.c] + map[nr][nc] - '0';

					if (sumMap[nr][nc] == -1 || sumMap[nr][nc] > newSum) {
						sumMap[nr][nc] = newSum;
						q.offer(new Node(nr, nc));
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(sumMap[N - 1][N - 1]).append("\n");
		}

		System.out.println(sb);
	}

}
