/*
 * 왼쪽으로 기울이기 -> 양쪽 다 좌로 이동.
 * 오른쪽으로 기울이기 -> 양쪽 다 우로 이동
 * 위쪽 -> 양쪽 다 위로 
 * 아래 -> 양쪽 다 아래 
 * 
 * 공은 동시에 움직임. 
 * 
 * 구멍 1개, 구슬 각 1개
 * 
 * 좌우로 어떻게 굴리지..? 
 *
 * 만약 10번 모두 동작 다 해본다면? 4^10 경우를 해보면 되고.
 * 
 * 각 케이스에서 깊은 복사 없이 O(1)에 할수만 있다면 
 * 4^10 = 1,048,576 이므로 문제 없음.
 * 근데 보드 에서 내가 가려는 부분은 반드시 봐야하니
 * 각 움직임당 최대 10번 상태를 봐야함. 
 * 4^10 * 20..
 * 
 * 그런데 이전 상태로 되돌리는 작업도 계속 필요한데,
 * 이건.. 매번 공 위치를 스캔하지 말고, 보드는 완전히 비워두고,
 * 공의 위치를 입력으로 받아서 dfs하면 될듯.
 * 
 * 다만 그렇게 할 때, 둘이 같은열, 혹은 같은 행에 있을 때 처리할 로직 별도 추가 필요함,
 * 
 * 그러면, 만약 r만 빠질 수 있는 경우에는.. return하고, cnt 기록
 * 
 * 그것보다 더 큰 cnt인 애들은 dfs 안하면 되고..
 * 
 * 그러면 dfs 파라미터는 각 공의 위치와, cnt.
 * 
 * 
 * 이론 식으로 탐색하면 4^10 * 20으로 가능할듯하다. 
 * 
 * 만약 두 공이 빠지면 유효하지 않은 return,
 * 
 * 만약 파란 공만 빠지면 유효하지 않은 return
 * 
 * boolean dfs(Pos r_ball, Pos b_ball, int cnt)
 * 
 * 
 * 같은 행 좌우 이동
 * 같은 열 상하 이동은 어떻게 처리하면 좋을까? 
 * 
 * 같은 행인데 좌면 왼쪽꺼부터, 같은 행인데 우면 오른쪽것 부터
 * 같은 열 상이면 위부터, 같은열 하면 아래부터 이동하면 문제가 없다.
 *
 * 그런데 이걸 정멸 4개 조건에 대해 다 적고 별개 처리 해야하나..?
 * 더 심플한 방법이 있으면 좋겠다.
 * 
 */


import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	
	final static int[] DR = {-1, 1, 0, 0};
	final static int[] DC = {0, 0, -1, 1};
	
	static char[][] map;
	static int H, W;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		ans = Integer.MAX_VALUE;
		
		int b_r = 0;
		int b_c = 0;
		int r_r = 0;
		int r_c = 0;
		
		for (int i = 0; i < H; i++) {
			String row = br.readLine();
			for (int j = 0; j < W; j++) {
				char ch = row.charAt(j);
				if (ch == 'B') {
					b_r = i;
					b_c = j;
					ch = '.';
				} else if (ch == 'R') {
					r_r = i;
					r_c = j;
					ch = '.';
				}
				map[i][j] = ch;
			}
		}
		
		dfs(b_r, b_c, r_r, r_c, 0);
		
		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
	}
	
	public static int[] move(int r, int c, int dr, int dc) {
		int count = 0;
		while (map[r + dr][c + dc] != '#' && map[r][c] != 'O') {
			r += dr;
			c += dc;
			count++;
		}
		return new int[] {r, c, count};
	}
	
	public static void dfs(int br, int bc, int rr, int rc, int cnt) {
		if (cnt >= 10) {
			return;
		}
		
		if (cnt >= ans) {
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int dr = DR[d];
			int dc = DC[d];
			
			int[] red = move(rr, rc, dr, dc);
			int[] blue = move(br, bc, dr, dc);
			
			boolean redInHole = map[red[0]][red[1]] == 'O';
			boolean blueInHole = map[blue[0]][blue[1]] == 'O';
			
			if (blueInHole) {
				continue;
			}
			
			if (redInHole) {
				ans = Math.min(ans, cnt + 1);
				return;
			}
			
			if (red[0] == blue[0] && red[1] == blue[1]) {
				if (red[2] > blue[2]) {
					red[0] -= dr;
					red[1] -= dc;
				} else {
					blue[0] -= dr;
					blue[1] -= dc;
				}
			}
			
			dfs(blue[0], blue[1], red[0], red[1], cnt + 1);
		}
				
	}
}
