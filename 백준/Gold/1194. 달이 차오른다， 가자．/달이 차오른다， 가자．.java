/*
 * bfs를 하는데 
 * 각 노드의 상태에는 
 * 상태에는 
 * 키의 보유 여부, 이동횟수가 있음.
 * 
 * 'a' ~ 'f' 이면 , 비트셋에 저장.
 * 'A' ~ 'f' 면 비트셋에 위 키가 저장되어 있으면 o 아니면 x.
 * 이외의 방문처리는 없고, 구간 벗어나거나 장애물 만나면 탐색 종료
 * 
 * 1에 가장 먼저 도달한 노드의 거리 반환.
 * 
 * 
 * 
 * 문제는 방문처리가 없으면 무한 루프를 돈다.
 * 키의 숫자가 바뀌지도 않았는데 같은 곳을 또왔다?
 * 최단이 아니다.
 * [r][c][key의 bit] visited 배열 관리
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static final int[] DR = { 0, 0, 1, -1 };
	static final int[] DC = { 1, -1, 0, 0 };
	static final int MAX_KEYS = 6;

	static class State {
		int r;
		int c;
		int dist;
		int keys;

		public State(int r, int c, int dist, int keys) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.keys = keys;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		char[][] grid = new char[H][W];
		int sr = -1;
		int sc = -1;

		for (int i = 0; i < H; i++) {
			String s = br.readLine().trim();
			for (int j = 0; j < W; j++) {
				char ch = s.charAt(j);
				if (ch == '0') {
					sr = i;
					sc = j;
					ch = '.';
				}
				grid[i][j] = ch;
			}
		}

		boolean[][][] visited = new boolean[H][W][1 << MAX_KEYS];
		Queue<State> q = new ArrayDeque<>();

		visited[sr][sc][0] = true;
		q.offer(new State(sr, sc, 0, 0));

		while (!q.isEmpty()) {
			State cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + DR[d];
				int nc = cur.c + DC[d];
				int nk = cur.keys;
				int nd = cur.dist + 1;

				if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
					continue;
				}

				char ch = grid[nr][nc];

				if (ch == '#')
					continue;

				if (ch == '1') {
					System.out.println(nd);
					return;
				} else if ('a' <= ch && ch <= 'f') {
					nk |= (1 << (ch - 'a'));
				} else if ('A' <= ch && ch <= 'F') {
					int need = 1 << (ch - 'A');
					if ((nk & need) == 0)
						continue;
				}

				if (!visited[nr][nc][nk]) {
					visited[nr][nc][nk] = true;
					q.offer(new State(nr, nc, nd, nk));
				}

			}

		}

		System.out.println(-1);

	}

}
