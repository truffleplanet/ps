/*
 * 
 * 연속이라는 조건이 없으므로 부분집합 탐색 후 합 구해보면 되겠다.
 * 
 * 2^20 --> 백만이니 2초 통과 가능 
 * 
 * 수열의 크기가 양수여야한다니깐 공집합은 제외하고.. 
 * 
 * 중복되는 파트들을 dp로 줄일 수도 있을 것 같은데..
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int S;
	static int[] seq;
	static int cnt;

	static void subset(int idx, int sum) {

		if (idx == N) {
			if (sum == S)
				cnt++;
			return;
		}

		subset(idx + 1, sum + seq[idx]);
		subset(idx + 1, sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		seq = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		cnt = 0;
		subset(0, 0);

		if (S == 0)
			cnt--;

		System.out.println(cnt);

	}

}
