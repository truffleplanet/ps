import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int r;
		int c;
		int depth;
		int type; // 0 은 물 , 1은 고슴도치

		public Node(int r, int c, int depth, int type) {
			super();
			this.r = r;
			this.c = c;
			this.depth = depth;
			this.type = type;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] dr = { 1, 0, -1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		char[][] map = new char[R][C];

		int endR = 0;
		int endC = 0;

		int dochiR = 0;
		int dochiC = 0;
		Queue<Node> q = new ArrayDeque<>(); // 큐 생성

		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = line.charAt(j);
				if (c == 'S') {
					map[i][j] = '.';
					dochiR = i;
					dochiC = j;
					continue;
				}
				if (c == 'D') {
					endR = i;
					endC = j;
				}

				if (c == '*') {
					q.offer(new Node(i, j, 0, 0));
				}
				map[i][j] = c;
			}
		}

		Node start = new Node(dochiR, dochiC, 0, 1); // 물을 먼저 넣고 고슴도치를 넣어야 해서 모든 입력이 끝난 후 고슴도치 입력함.
		q.offer(start);

		while (!q.isEmpty()) { // 기본 조건은 q가 비어있지 않았을 때이지만, 탐색이 성공하면 바로 return
			Node u = q.poll();
			int r = u.r;
			int c = u.c;
			int depth = u.depth;
			int type = u.type;
			if (type == 1 && r == endR && c == endC) {
				System.out.println(depth);
				return; // 실행종료.
			}

			for (int t = 0; t < 4; t++) {
				int nr = r + dr[t];
				int nc = c + dc[t];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;

				if (map[nr][nc] == 'X')
					continue;

				if (type == 0 && (map[nr][nc] == '.' || map[nr][nc] == '-')) {
					map[nr][nc] = '*';
					q.add(new Node(nr, nc, depth + 1, type));
				}
				if (type == 1 && map[nr][nc] != '*' && map[nr][nc] != '-') {
					map[nr][nc] = '-'; // 방문처리 물은 퍼질 수 있게..
					q.add(new Node(nr, nc, depth + 1, type)); // 이건 타입 0이라 퍼지지는 않으니 걱정 x
				}

			}

		}

		System.out.println("KAKTUS");
	}
}
