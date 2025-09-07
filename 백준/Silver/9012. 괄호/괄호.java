/*
 * 
 * - 불변: 매칭 가능한 열린 괄호와 닫힌 괄호의 쌍이 있을 때, 항상 열린 괄호가 왼쪽에 있다. 

 * 1. Brute Force
 * - 시작이 닫힌 괄호면 false.  
 * - 왼쪽부터 읽으며 닫힌 괄호가 등장할 때까지 읽는다. 
 * - 닫힌 괄호가 등장하면, 포인터 하나 더 만들고, 그게 바로 왼쪽 가리키도록 한 후
 * - 각 포인터가 반대 방향으로 이동. 왼쪽 열린 괄호와 오른쪽 닫힌 괄호 하나씩 매칭시킨다. 
 * - 오른쪽 포인터가 배열의 끝에 다다르거나, 열린 괄호를 가리키고 있으면 위의 과정 처음부터 반복 
 * - 과정이 끝나고 모든 괄호가 매칭되었으면 true 
 * 
 * 
 * 2. Stack
 * - 위 과정을 잘 생각해보면, 앞에서 뒤로 읽는다면 열린괄호, 뒤에서 앞으로 읽는다면 닫힌 괄호가 등장할 때에는 검사할 것이 없음.
 * - 우리는 앞에서 뒤로 읽으며 문제를 풀어보기로 하자. 
 * - 시작이 닫힌 괄호면 false
 * - 닫힌 괄호가 등장할 때까지 stack에 push.
 * - 닫힌 괄호가 등장하면 stack.pop() 만약 stack이 비어있다면 false
 * - 반복.
 * - 끝난 후 empty아니면 false
 * 
 * 3. Counting
 * - 근데 잘 생각해보면 스택도 필요없고, 그냥 카운팅만 해도 문제 없겠다
 * - 왼쪽 부터 읽으며 열린 괄호 숫자 세고.
 * - 닫힌 괄호 등장하면 뺴고
 * - <0 되면 false, 
 * - 끝난 후 0아니면 false
 *  
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		final String YES = "YES";
		final String NO = "NO";

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			char[] vps = br.readLine().trim().toCharArray();

			byte cnt = 0; // 그냥 써보고 싶어서.. -128~127이니 가능
			for (int p = 0; p < vps.length; p++) {
				if (vps[p] == '(') {
					cnt++;
				} else if (vps[p] == ')') {
					if (--cnt < 0)
						break;
				} else {
					throw new Exception();
				}
			} // end of for-loops

			// 조건 확인

			if (cnt == 0) {
				sb.append(YES).append('\n');
			} else {
				sb.append(NO).append('\n');
			}
		} // 테케 종료

		System.out.println(sb);

	}

}