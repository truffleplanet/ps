import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
 * 최대 경우의 수 -> 4^5 = 1024
 * 
 * 조건
 * - 이동하려고 하는 쪽의 칸이 먼저 합쳐진다
 * - 한 번의 이동에서 이미 합쳐진 블록은 또 합쳐질 수 없다
 * 
 * - 일단 밀고, 그 다음에 처리하면 됨. 스택에 쌓아 뒀다가.
 * 
 * 
 * [풀이]
 * 1. 4방향으로 밀어서 상태 변화 시키는 로직
 * 	 - 이전 상태에서 복제? 아니면 더 우아한 방법 있는지.?
 * 	
 * 2. 완전탐색 로직(백트래킹)
 * 
 */

public class Main {

	static final int UP = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	static final int DOWN = 3;
	
	static final int TOTAL_MOVE = 5;

	static int N;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		ArrayDeque<Integer>[] startState = new ArrayDeque[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			startState[i] = new ArrayDeque<Integer>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == 0) {
					continue;
				}
				startState[N + j].offerLast(val);
				startState[i].offerLast(val);
			}
		}
		
		ans = 0;
		recur(0, startState);
		System.out.println(ans);
		
	}
	
	static void recur(int cnt, ArrayDeque<Integer>[] state) {
		if (cnt == TOTAL_MOVE) {
			for (int i = 0; i < N; i++) {
				while (!state[i].isEmpty()) {
					ans = Math.max(ans, state[i].pop());
				}
			}
			return;
		}
		
		for (int dir = 0; dir < 4; dir++) {
			ArrayDeque<Integer>[] nextState = move(state, dir);
			recur(cnt + 1, nextState);
		}
	}

	static ArrayDeque<Integer>[] move(ArrayDeque<Integer>[] origState, int dir) {
		
		ArrayDeque<Integer>[] state = new ArrayDeque[2 * N];
		ArrayDeque<Integer>[] output = new ArrayDeque[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			state[i] = origState[i].clone();
			output[i] = new ArrayDeque<>();
		}
		
		switch (dir) {
		case LEFT:
			for (int i = 0; i < N; i++) {
				if (state[i].isEmpty()) {
					continue;
				}
				int prev = state[i].pollFirst();

				while (!state[i].isEmpty()) {
					int curr = state[i].pollFirst();

					if (prev == -1) {
						prev = curr;
						continue;
					}

					if (curr == prev) {
						output[N + output[i].size()].offerLast(curr * 2);
						output[i].offerLast(curr * 2);
						prev = -1;
					} else {
						output[N + output[i].size()].offerLast(prev);
						output[i].offerLast(prev);
						prev = curr;
					}
				}

				if (prev != -1) {
					output[N + output[i].size()].offerLast(prev);
					output[i].offerLast(prev);
				}
			}
			break;
		case RIGHT:
			for (int i = 0; i < N; i++) {
				if (state[i].isEmpty()) {
					continue;
				}
				int prev = state[i].pollLast();

				while (!state[i].isEmpty()) {
					int curr = state[i].pollLast();

					if (prev == -1) {
						prev = curr;
						continue;
					}

					if (curr == prev) {
						output[2 * N - 1 - output[i].size()].offerLast(curr * 2);
						output[i].offerFirst(curr * 2);
						prev = -1;
					} else {
						output[2 * N - 1 - output[i].size()].offerLast(prev);
						output[i].offerFirst(prev);
						prev = curr;
					}
				}

				if (prev != -1) {
					output[2 * N - 1 - output[i].size()].offerLast(prev);
					output[i].offerFirst(prev);
				}
			}
			break;
		case UP:
			for (int i = N; i < 2 * N; i++) {
				if (state[i].isEmpty()) {
					continue;
				}
				int prev = state[i].pollFirst();

				while (!state[i].isEmpty()) {
					int curr = state[i].pollFirst();

					if (prev == -1) {
						prev = curr;
						continue;
					}

					if (curr == prev) {
						output[output[i].size()].offerLast(curr * 2);
						output[i].offerLast(curr * 2);
						prev = -1;
					} else {
						output[output[i].size()].offerLast(prev);
						output[i].offerLast(prev);
						prev = curr;
					}
				}

				if (prev != -1) {
					output[output[i].size()].offerLast(prev);
					output[i].offerLast(prev);
				}
			}
			break;
		case DOWN:
			for (int i = N; i < 2 * N; i++) {
				if (state[i].isEmpty()) {
					continue;
				}
				int prev = state[i].pollLast();

				while (!state[i].isEmpty()) {
					int curr = state[i].pollLast();

					if (prev == -1) {
						prev = curr;
						continue;
					}

					if (curr == prev) {
						output[N - 1 - output[i].size()].offerLast(curr * 2);
						output[i].offerFirst(curr * 2);
						prev = -1;
					} else {
						output[N - 1 - output[i].size()].offerLast(prev);
						output[i].offerFirst(prev);
						prev = curr;
					}
				}

				if (prev != -1) {
					output[N -  1 - output[i].size()].offerLast(prev);
					output[i].offerFirst(prev);
				}
			}
			break;
		}
		
		return output;
	}
}
