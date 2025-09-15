import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 정의:
 * N개에서 R개를 뽑아라 -> 조합
 * 
 * 제약 조건:
 * 사전순으로 정렬된 채로 출력해라
 * 
 * 풀이:
 * 오름차순으로 들어오니깐, 뽑는 인덱스도 오름차순으로 유지되게만 하면 되겠다.
 */

public class Main {

	static int[] seq;
	static int[] path;
	static int N;
	static StringBuilder sb;
	static final int R = 6;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		String s = br.readLine();
		while (s.charAt(0) != '0') {
			st = new StringTokenizer(s);
			N = Integer.parseInt(st.nextToken());
			seq = new int[N];
			path = new int[R];
			for (int i = 0; i < N; i++) {
				seq[i] = Integer.parseInt(st.nextToken());
			}
			combination(0, 0);
			sb.append('\n');
			s = br.readLine();
		}

		System.out.println(sb);
	}

	static void combination(int cnt, int start) {
		if (cnt == R) {
			for (int i = 0; i < R; i++) {
				sb.append(path[i]).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = start; i < N; i++) {
			path[cnt] = seq[i];
			combination(cnt + 1, i + 1);
		}
	}

}
