import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	final static int DECK_SIZE = 9;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] tokens = br.readLine().split(" ");
			int[] fixed = new int[DECK_SIZE]; // 규영이가 내는 카드, 순서

			boolean[] used = new boolean[19]; // 남은 카드

			for (int i = 0; i < DECK_SIZE; i++) {
				int card = Integer.parseInt(tokens[i]);
				fixed[i] = card;
				used[card] = true;
			}

			int[] score = new int[2];
			int[] deck = new int[DECK_SIZE];
			recursion(0, fixed, deck, used, score);
			System.out.println("#" + t + " " + score[0] + " " + score[1]);
		}
	}

	public static void recursion(int cnt, int[] fixed, int[] deck, boolean[] used, int[] scoreboard) {
		if (cnt == DECK_SIZE) {
			game(fixed, deck, scoreboard);
			return;
		}

		for (int i = 1; i < 19; i++) {
			if (used[i])
				continue;
			deck[cnt] = i;
			used[i] = true;
			recursion(cnt + 1, fixed, deck, used, scoreboard);
			used[i] = false;
		}
	}

	public static void game(int[] p1, int[] p2, int[] scoreboard) {
		int p1Score = 0;
		int p2Score = 0;
		for (int i = 0; i < 9; i++) {
			if (p1[i] > p2[i]) {
				p1Score += p1[i] + p2[i];
			} else { // 카드가 같은 경우는 없음
				p2Score += p1[i] + p2[i];
			}
		}
		if (p1Score > p2Score) {
			scoreboard[0] += 1; // 승리
		} else if (p2Score > p1Score) {
			scoreboard[1] += 1; // 패배
		}

	}
}
