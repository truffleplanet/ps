/*
 * 전부 양수이니 덧셈인 것은 상관없고, 
 * 뺄셈일 때가 고민지점임.
 * 뺄셈일 때 그 뒤의 항들을 괄호로 잘 묶어주는게 중요함.
 * 
 * 숫자 목록과 연산 목록을 가지고 있다고 한다면..
 * 
 * 뺄셈 뒤의 덧셈은 전부 묶어주고,
 * 
 * 그 외에는 그대로.
 * 
 * 즉 뺼셈이 오기 전까지는 모두 묶어서 계산하기.
 *
 *
 * 그럼 뒤부터 읽자..
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] expr = br.readLine().toCharArray();

		StringBuilder sb = new StringBuilder();

		int ans = 0;
		int temp = 0;
		for (int i = expr.length - 1; i >= 0; i--) {
			char c = expr[i];
			if (c == '+') {
				int x = Integer.parseInt(sb.reverse().toString());
				sb = new StringBuilder();
				temp += x;
			} else if (c == '-') {
				int x = Integer.parseInt(sb.reverse().toString());
				temp += x;
				ans -= temp;
				temp = 0;
				sb = new StringBuilder();
			} else {
				sb.append(c);
			}
		}
		temp += Integer.parseInt(sb.reverse().toString());
		ans += temp;

		System.out.println(ans);

	}
}
