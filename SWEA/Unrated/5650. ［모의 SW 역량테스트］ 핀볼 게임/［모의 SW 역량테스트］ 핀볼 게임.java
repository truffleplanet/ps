import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	static final int UP = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	static final int DOWN = 3;

	static Block[][] map;
	static Map<Integer, WormHole> wormholes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			map = new Block[N + 2][N + 2]; // 상하좌우 패딩
			wormholes = new HashMap<>();
			for (int p = 1; p < map.length; p++) {
				map[0][p] = new Block(5, 0, p);
				map[map.length - 1][p] = new Block(5, map.length - 1, p);
				map[p][0] = new Block(5, p, 0);
				map[p][map.length - 1] = new Block(5, p, map.length - 1);
			}

			for (int i = 1; i < N + 1; i++) { // 지도 입력
				StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
				for (int j = 1; j < N + 1; j++) {
					int type = Integer.parseInt(st.nextToken());
					if (type == 0) { // 아무것도 없는 경우 null로 그대로 둔다.
						continue;
					}
					if (type >= 6 && type <= 10) { // 웜홀
						WormHole worm = new WormHole(type, i, j);
						map[i][j] = worm;
						if (wormholes.containsKey(type)) {
							wormholes.get(type).pairing(worm);
						} else {
							wormholes.put(type, worm);
						}
					} else if (type == -1) { // 블랙홀
						map[i][j] = new BlackHole(type, i, j);
					} else { // 일반 블록
						map[i][j] = new Block(type, i, j);
					}
				}
			}

//			for (Block[] line : map) {
//				System.out.println(Arrays.toString(line));
//			}

			PinBall pb = PinBall.getInstance();
			int maxScore = 0;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) { // 공의 시작 위치
					if (map[i][j] != null)
						continue;
					for (int d = 0; d < 4; d++) { // 공의 방향
						pb.startR = pb.r = i;
						pb.startC = pb.c = j;
						pb.state = d;
						pb.score = 0;
						pb.game = true;

						while (pb.game) {
							pb.move();
						}
						maxScore = Math.max(maxScore, pb.score);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);

	}

	static class PinBall {
		int startR;
		int startC;
		int state;
		int r;
		int c;
		int score;
		boolean game;
		private static PinBall instance = new PinBall();

		private PinBall() {
		}

		public static PinBall getInstance() {
			return instance;
		}

		public void move() {
			int nr = r;
			int nc = c;
			switch (state) {
			case UP:
				nr--;
				break;
			case DOWN:
				nr++;
				break;
			case LEFT:
				nc--;
				break;
			case RIGHT:
				nc++;
				break;
			}

			if (nr == startR && nc == startC) {
				game = false;
				return;
			}

			if (map[nr][nc] == null) {
				r = nr;
				c = nc;
				return;
			}
			if (map[nr][nc] instanceof Block) {
				map[nr][nc].collision(this);
				return;
			}
		}
	}

	static class Block {
		static int[][] collisionTable = { {}, { DOWN, UP, LEFT, RIGHT }, { RIGHT, DOWN, LEFT, UP },
				{ LEFT, RIGHT, DOWN, UP }, { DOWN, RIGHT, UP, LEFT }, { DOWN, RIGHT, LEFT, UP } };
		int type;
		int r;
		int c;

		public Block(int type, int r, int c) {
			super();
			this.type = type;
			this.r = r;
			this.c = c;
		}

		public void collision(PinBall pb) {
			if (type >= 1 && type <= 5) {
				pb.state = collisionTable[this.type][pb.state];
				pb.score++; // 블록, 벽에 충돌시에만 점수가 증가한다.
				pb.r = this.r;
				pb.c = this.c;
			}
		}

		@Override
		public String toString() {
			return "" + type;
		}

	}

	static class WormHole extends Block {
		WormHole next;

		public WormHole(int type, int r, int c) {
			super(type, r, c);
		}

		public void pairing(WormHole next) {
			this.next = next;
			next.next = this;
		}

		@Override
		public void collision(PinBall pb) {
			pb.r = this.next.r;
			pb.c = this.next.c;
			return;
		}
	}

	static class BlackHole extends Block {

		public BlackHole(int type, int r, int c) {
			super(type, r, c);
		}

		@Override
		public void collision(PinBall pb) {
			pb.game = false;
			return;
		}
	}
}
