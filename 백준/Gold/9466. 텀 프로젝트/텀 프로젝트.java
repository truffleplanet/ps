/*
 * 모든 학생은 팀원 1명을 선택할 수 있음.
 * 팀원 수에 제한은 없음.
 * 
 * 주어진 선택의 결과를 보고 어느 프로젝트 팀에도 속하지 않는 학생들의 수를 
 * 계산하는 프로그램. 
 * 
 * 2 -> 1 -> 3, 3->3 5->3  3만 한 팀이고
 * 1, 2, 5는 팀이 아님. 
 * 6 -> 4 -> 7 -> 6 (4, 7, 6)
 * 
 * 즉 팀 해석 방법은.. 사이클이 생겨야지 팀이다. 
 * 중간에 끊기면 팀 아님.
 * 
 * (1, 3), (2, 1), (3, 3), (4, 7), (5, 3), (6, 4), (7, 6)
 * 
 * 단순 무식하게는, 모든 노드에서 dfs하며, 자기 자신으로 돌아오는 길 있나 판단하면 된다.
 * 100,000^2이므로 그렇게 풀면 안되고..
 * 
 * [2, 1, 5, 3, 1] 이런 식 path 따라 왔다면, 1, 5, 3은 한 팀이고 2는 다시 볼 필요 없다.
 * 그 후에는 그러면, 남은 별개의 요소들을 탐색하되, 이미 방문한 노드들은 가볼 필요가 없다.
 * 
 * 이걸 그냥 하면 느리고,..
 * 
 * 
 * int[] state 
 * 
 * -2 방문 완료
 * -1 미 방문
 * 그 외에는 경로 stack의 index 번호. 
 * 
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] choice = new int[N + 1];
			int[] state = new int[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				choice[i] = Integer.parseInt(st.nextToken());
				state[i] = -1;
			}
			
			Deque<Integer> stack = new ArrayDeque<>();
			int cnt = 0;
			
			for (int i = 1; i <= N; i++) {
				if (state[i] == -1) {
					stack.push(i);
					state[i] = 0;
					while (true) {
						int next = choice[stack.peek()];
						if (state[next] == -1) {
							state[next] = stack.size();
							stack.push(next);
						} else if (state[next] == -2) {
							break;
						} else {
							int cycleStart = state[next];
							while (stack.size() > cycleStart) {
								state[stack.pop()] = -2;
							}
							break;
						}
					}
					
					while (!stack.isEmpty()) {
						cnt++;
						state[stack.pop()] = -2;
					}
				}
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.println(sb);
	}
	
}
