import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.StringTokenizer;

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

	static ArrayDeque<Integer>[] move(ArrayDeque<Integer>[] state, int dir) {
				ArrayDeque<Integer>[] output = new ArrayDeque[2 * N];
		for (int i = 0; i < 2 * N; i++) {
			output[i] = new ArrayDeque<>();
		}
		
		switch (dir) {
		case LEFT:
			for (int i = 0; i < N; i++) {
				Iterator<Integer> iterator = state[i].iterator();
				if (!iterator.hasNext()) {
					continue;
				}
				int prev = iterator.next();

				while (iterator.hasNext()) {
					int curr = iterator.next();

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
				Iterator<Integer> iterator = state[i].descendingIterator();
				if (!iterator.hasNext()) {
					continue;
				}
				int prev = iterator.next();

				while (iterator.hasNext()) {
					int curr = iterator.next();

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
				Iterator<Integer> iterator = state[i].iterator();
				if (!iterator.hasNext()) {
					continue;
				}
				int prev = iterator.next();

				while (iterator.hasNext()) {
					int curr = iterator.next();

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
				Iterator<Integer> iterator = state[i].descendingIterator();
				if (!iterator.hasNext()) {
					continue;
				}
				int prev = iterator.next();

				while (iterator.hasNext()) {
					int curr = iterator.next();

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
