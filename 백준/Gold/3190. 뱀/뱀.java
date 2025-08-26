import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	// 왼쪽으로 돌리면 반시계방향,
	// 오른쪽으로 돌리면 시계방향.

	// 충돌 여부는 몸체를 전부 순회해도 되지만(커봤자 100임)
	// 지도에 tail위치와 head위치 추적하면서, 현재 뱀 위치를 간단하게 추적할 수 있음.

	final static int[] DR = { 0, 1, 0, -1 };
	final static int[] DC = { 1, 0, -1, 0 };
	static int[][] map;
	static int N, K, L;

	static class Snake {

		static class Point {
			int r;
			int c;

			public Point(int r, int c) {
				super();
				this.r = r;
				this.c = c;
			}
		}

		Deque<Point> body;
		int dir = 0;

		public Snake() {
			super();
			this.body = new ArrayDeque<>();
			body.offerFirst(new Point(0, 0));
			map[0][0] = 2;
		}

		public void changeDir(int command) {
			switch (command) {
			case 'L':
				dir--;
				if (dir == -1) {
					dir = 3;
				}
				break;
			case 'D':
				dir++;
				if (dir == 4) {
					dir = 0;
				}
				break;
			}
		}

		public boolean move() {
			Point head = body.peekFirst();
			Point next = new Point(head.r + DR[dir], head.c + DC[dir]);

			if (next.r < 0 || next.r >= N || next.c < 0 || next.c >= N) // 벽에 부딪히면
				return false;

			if (map[next.r][next.c] == 2) { // 자기 몸을 만나면
				return false;
			}

			if (map[next.r][next.c] == 0) { // 일반적 움직임
				map[next.r][next.c] = 2;
				body.offerFirst(next);
				Point tail = body.pollLast();
				map[tail.r][tail.c] = 0;
			}

			if (map[next.r][next.c] == 1) {
				map[next.r][next.c] = 2;
				body.offerFirst(next);
			}
			return true;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		// 지도 입력받기
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // to 0-based idx
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 1; // 사과 1
		}

		// 명령 입력 받기, 시간순 정렬 보장
		L = Integer.parseInt(br.readLine());
		int[][] commands = new int[L + 1][2];
		commands[L][0] = Integer.MAX_VALUE;
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int cmd = st.nextToken().charAt(0);
			commands[i][0] = t;
			commands[i][1] = cmd;
		}

		Snake s = new Snake();

		int t = 0;
		int cmdIDX = 0;
		while (true) {
			if (commands[cmdIDX][0] == t) {
				s.changeDir(commands[cmdIDX][1]);
				cmdIDX++;
			}

			t++;
			boolean a = s.move();
//			printMap();
			if (a)
				continue;
			else
				break;
		}

		System.out.println(t);

	}

//	public static void printMap() {
//		System.out.println(" ");
//		for (int[] k : map) {
//			System.out.println(Arrays.toString(k));
//		}
//	}

}
