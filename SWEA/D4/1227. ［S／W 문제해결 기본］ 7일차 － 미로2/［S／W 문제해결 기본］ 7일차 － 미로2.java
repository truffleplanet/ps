import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		final int[] DR = {0, 0, -1, 1};
		final int[] DC = {1, -1, 0, 0};
		
		
		int T = 10;
		int N = 100;
		
		char[][] map = new char[N][N];
		
		for (int na = 1; na <= T; na++) {
			int tc = Integer.parseInt(br.readLine());
			
			int[] start = new int[2]; // r, c, level
		
			for (int i = 0; i< 100; i++) {
				String line = br.readLine();
				for (int j = 0; j < 100; j++) {
					char c = line.charAt(j);
					if (c == '2') {
						start[0] = i;
						start[1] = j;
					}
					map[i][j]= c;
				}
			}// 입력 종료
			
			
			Queue<int[]> q = new ArrayDeque<>(); 
			q.offer(start);
			map[start[0]][start[1]] = '9'; //방문처리
			
			int ans = 0;
			boolean isOn = true;
			while (!q.isEmpty()) {
				if (!isOn) 
					break;
				
				int[] u = q.poll();
							
				
				for (int i = 0; i < 4; i++) {
					int nr = u[0] + DR[i];
					int nc = u[1] + DC[i];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					
					if (map[nr][nc] == '1')
						continue;
					
					if(map[nr][nc] == '3') {
						isOn = false;
						ans = 1;
						break;
					}
					
					
					map[nr][nc] = '1';
					q.offer(new int[] {nr, nc});					
				}
				
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
		}
		
		System.out.println(sb);
	}
}
