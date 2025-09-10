/*
 * key - id, value - name
 * 
 * key로 value를 조회하거나, value로 key를 조회하거나 둘 모두 지원하는 데이터 구조 설계하기.
 * 
 * 풀이:
 * String:int인 hash와
 * int:String인 hash 각각 관리하기 
 * 근데 int:string인 경우는 현재 id가 1부터 차례대로 입력되니, arr로 관리.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> getId = new HashMap<>();
		String[] getName = new String[N + 1];

		for (int id = 1; id <= N; id++) {
			String name = br.readLine();
			getName[id] = name;
			getId.put(name, id);
		}

		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			char peek = input.charAt(0);
			if (peek >= '0' && peek <= '9') {
				sb.append(getName[Integer.parseInt(input)]);
			} else {
				sb.append(getId.get(input));
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

}
