/*
 * 4방향 탐색은 하겠는데, 출력 형식에 맞춰서 어디서 뭘 추가해야 할 지 헷갈린다.
 * 
 * 기본 로직은, 네개가 같으면 같다고 표시만 해두기.
 * 4개가 다르면, 괄호내에 기록하기.
 * StringBuilder로 하는게 적절할 것 같지만 일단 편의상 String구현부터 해보자.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb;
	static char[][] map;

	// inclusive range
	static void recursion(int r, int endR, int c, int endC) {

		char first = map[r][c];
		boolean same = true;
		for (int i = r; i <= endR; i++) {
			for (int j = c; j <= endC; j++) {
				if (map[i][j] != first) {
					same = false;
					break;
				}
				if (!same)
					break;
			}
		}

		if (same) {
			sb.append(first);
			return;
		}

		int halfR = (r + endR) / 2;
		int halfC = (c + endC) / 2;
		sb.append("(");
		recursion(r, halfR, c, halfC); // 왼쪽 위
		recursion(r, halfR, halfC + 1, endC); // 오른쪽 위
		recursion(halfR + 1, endR, c, halfC); // 왼쪽 아래
		recursion(halfR + 1, endR, halfC + 1, endC); // 오른쪽 아래
		sb.append(")");
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().trim().toCharArray();
		}

		recursion(0, N - 1, 0, N - 1);
		System.out.println(sb);

	}
}
