/*
 * 각 카테고리 집합별로 다음과 같은 선택지가 있음.
 * n개 중 1개 선택, 선택하지 않음.
 * 따라서 각 집합별 선택지는 n+1개임.
 * 
 *  
 *  따라서 정답은 (n_a + 1) * (n_b + 1) *... 인데
 * 그런데 모든 집합에서 전부 선택하지 않으면 알몸이니 위 결과에 -1 해주면 됨.
 * 
 * 
 * 입력 방식:
 * 카테고리는 뒤에 주어짐.
 * 같은 이름을 가진 의상은 없다고 하니깐. 의상 이름은 그냥 읽지 말고
 * 카테고리를 key로 value를 int로 하는 hashmap사용.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		HashMap<String, Integer> map = new HashMap<>();

		for (int $ = 0; $ < T; $++) {
			map.clear();
			int N = Integer.parseInt(br.readLine());

			if (N == 0) {
				sb.append(0).append('\n');
				continue;
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String cat = st.nextToken();
				map.putIfAbsent(cat, 0);
				int prev = map.get(cat);
				map.replace(cat, prev + 1);
			}

			int ans = 1;
			for (int n : map.values()) {
				ans *= n + 1;
			}

			ans -= 1;

			sb.append(ans).append('\n');
		}

		System.out.println(sb);
	}

}
