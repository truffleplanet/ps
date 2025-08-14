/*
 * 문제 조건
 * 로봇이 매 step별 할 수 있는 동작은 
 * - 좌우로 한칸 이동
 * - 버튼 누르기
 * - 아무 것도 안하기
 * 
 * 두 로봇이 해야하는 것
 * - 수열에 표시된 순서대로 버튼을 누르기
 * - 제약 - 동시에 버튼을 누를 수는 없다.
 * 
 * - B 2 O 1 O 2 B 4 
 * 		     blue    orange
 *   step 1   +1 	  stop
 *   step 2  btn(2)   stop
 *   step 3   +1      btn(1)
 *   step 4   +1       +1
 *   step 5   stop    btn(2)
 *   step 6   btn(4)  stop
 *   end..
 *   
 *   
 *   여기서 얻을 수 있는 인사이트
 *   - 항상 다음 명령으로 이동하고
 *   - 버튼은 시킨 순서대로 누르면
 *   - 문제될 것이 없다.
 *   
 *   순차적으로 모든 일이 일어날 필요가 없다
 *   양 로봇의 움직임을 비동기로 처리해보자.
 *   
 *   
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	final static String BLUE = "B";
	final static String ORANGE = "O";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) { // 테케
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 명령의 갯수
			Command[] commands = new Command[N];
			for (int i = 0; i < N; i++) {
				int type = (st.nextToken().equals("B")) ? 1 : 0; // orange:0, blue:1
				int btn = Integer.parseInt(st.nextToken());
				commands[i] = new Command(type, btn);
			}

			int pos[] = { 1, 1 }; // idx: type
			int prevTime[] = { 0, 0 };
			int t = 0;
			// 일단 지금 주어진 명령을 처리함.
			// 시간 계산을.. 둘이 별개로 한다
			// 시간 두 개를 관리하며
			// 한 명령이
			// 명령이 끝날 때마다
			for (Command command : commands) {
				int diff = Math.abs(command.btn - pos[command.type]);
				if (diff > t - prevTime[command.type]) { // 한번에 움직이기
					// t - prevTime[command.type] 가 한번에 이동할 수 있는 양이니
					// 총 이동해야하는 양인 diff에서 그만큼을 빼면
					// 그게 추가해야하는 시간이다.
					t += diff - (t - prevTime[command.type]); // 이동 시간 추가
				}
				t += 1; // 버튼 누르기
				prevTime[command.type] = t; // 시간 갱신
				pos[command.type] = command.btn; // 위치 갱신
			}

			sb.append("#").append(tc).append(" ").append(t).append("\n");
		}

		System.out.println(sb);

	}

	static class Command {
		int type;
		int btn;

		public Command(int type, int btn) {
			super();
			this.type = type;
			this.btn = btn;
		}

	}

}
