import java.util.ArrayList;
import java.util.List;
import java.util.*;

class Solution {

	int N;
	int R;
	int[][] dice;

	int bestScore;
	int[] ans;
	int MAX;

	public int[] solution(int[][] dice) {
		this.N = dice.length;
		this.R = N / 2;
		this.dice = dice;

		this.bestScore = -1;
		this.ans = new int[R];
		this.MAX = R * 100;

		comb(0, 0, new boolean[N]);

		return ans;
	}

	public void comb(int depth, int start, boolean[] selected) {
		if (depth == R) {
			calculateScore(selected);
			return;
		}

		for (int i = start; i < N; i++) {
			if (!selected[i]) {
				selected[i] = true;
				comb(depth + 1, i + 1, selected);
				selected[i] = false;
			}
		}
	}

	public void calculateScore(boolean[] selected) {
		List<int[]> diceA = new ArrayList<>();
		List<int[]> diceB = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				diceA.add(dice[i]);
			} else {
				diceB.add(dice[i]);
			}
		}

		List<Integer> sumA = new ArrayList<>();
		List<Integer> sumB = new ArrayList<>();

        
		makeSumList(0, 0, diceA, sumA);
		makeSumList(0, 0, diceB, sumB);

		int[] freq = new int[MAX + 1];
		for (int x : sumB) {
			freq[x]++;
		}

		int[] accum = new int[MAX + 1];
		accum[0] = freq[0];
		for (int i = 1; i <= MAX; i++) {
			accum[i] = accum[i - 1] + freq[i];
		}

		int score = 0;
		for (int x : sumA) {
			score += accum[x - 1];
		}

		if (score > bestScore) {
			bestScore = score;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					ans[idx++] = i + 1; // 1-based
				}
			}
		}

	}

	public void makeSumList(int depth, int sum, List<int[]> diceList, List<Integer> sumList) {
		if (depth == R) {
			sumList.add(sum);
			return;
		}

		int[] curDice = diceList.get(depth);

		for (int i = 0; i < 6; i++) {
			makeSumList(depth + 1, sum + curDice[i], diceList, sumList);
		}
	}

}

