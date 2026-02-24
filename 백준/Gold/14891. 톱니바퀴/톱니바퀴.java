import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 1. 움직이기 전에, 움직일 목록 뽑기.
 * 2. Array base의 deque를 쓰면 되지만, collection deque는 인덱스 기반 조회가 불가
 * 3. 직접 List 구현(Linked List이고 좌우 추적) vs 좌우 인덱스만 추적
 * 
 */

public class Main {

	final static int COG_TEETH = 8;
	final static int COG_NUM = 4;

	static class Cog {
		int leftIdx;
		int rightIdx;
		boolean[] state;

		public Cog() {
			leftIdx = 6;
			rightIdx = 2;
			state = new boolean[COG_TEETH];
		}
		
		public boolean getHead() {
			int headIdx = (rightIdx - 2 + COG_TEETH) % COG_TEETH;
			return state[headIdx];
		}

		public boolean getLeft() {
			return state[leftIdx];
		}

		public boolean getRight() {
			return state[rightIdx];
		}

		public boolean diffWithLeft(Cog x) {
			if (this.getLeft() == x.getRight()) {
				return false;
			}
			return true;
		}

		public boolean diffWithRight(Cog x) {
			if (this.getRight() == x.getLeft()) {
				return false;
			}
			return true;
		}
		
		public void turn(int dir) {
			int d = 1;
			if (dir == 1) {
				d = - 1;
			}
			leftIdx = (leftIdx + d + COG_TEETH) % COG_TEETH;
			rightIdx = (rightIdx + d + COG_TEETH) % COG_TEETH;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Cog[] cogs = new Cog[COG_NUM + 1]; // 1-based
		
		for (int cogIdx = 1; cogIdx <= COG_NUM; cogIdx++) {
			Cog cog = new Cog();
			cogs[cogIdx] = cog;

			String line = br.readLine();
			for (int i = 0; i < COG_TEETH; i++) {
				cog.state[i] = (line.charAt(i) == '1');
			}
		} 
		
		int N = Integer.parseInt(br.readLine());
		
		for (int cmdCnt = 0; cmdCnt < N; cmdCnt++) {
			st = new StringTokenizer(br.readLine());
			int targetIdx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			boolean[] needToTurn = new boolean[5];
			int[] dirs = new int [5];
			needToTurn[targetIdx] = true;
			dirs[targetIdx] = dir;
			
			// left - compare (i, i-1)
			for (int cogIdx = targetIdx; cogIdx > 1; cogIdx--) {
				if (cogs[cogIdx].diffWithLeft(cogs[cogIdx - 1])) {
					needToTurn[cogIdx - 1] = true;
					dirs[cogIdx - 1] = -dirs[cogIdx];
				} else {
					break;
				}
			}
			
			// right - compare (i, i + 1)
			for (int cogIdx = targetIdx; cogIdx < COG_NUM; cogIdx++) {
				if (cogs[cogIdx].diffWithRight(cogs[cogIdx + 1])) {
					needToTurn[cogIdx + 1] = true;
					dirs[cogIdx + 1] = -dirs[cogIdx];
				} else {
					break;
				}
			}
			
			// turn
			for (int cogIdx = 1; cogIdx <= COG_NUM; cogIdx++) {
				if (needToTurn[cogIdx]) {
					cogs[cogIdx].turn(dirs[cogIdx]);
				}
			}			
		}
		
		// 정답 계산 
		
		int ans = 0;
		int score = 1;
		for (int cogIdx = 1; cogIdx <= COG_NUM; cogIdx++) {
			if (cogs[cogIdx].getHead()) {
				ans += score;
			}
			score = (score << 1);
		}
		System.out.println(ans);
	}

}
