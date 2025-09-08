import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder out = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			char[] cmds = br.readLine().trim().toCharArray();
			int N = Integer.parseInt(br.readLine());
			String strArr = br.readLine().trim();
			strArr = strArr.substring(1, strArr.length() - 1);
			String[] tokens = strArr.split(",");

			Deque<Integer> dq = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				dq.addLast(Integer.parseInt(tokens[i]));
			}
//			System.out.println(dq);

			int reverse = 0;
			sb.append('[');
			for (int i = 0; i < cmds.length; i++) {
				if (cmds[i] == 'R') {
					reverse ^= 1;
				} else {
					if (dq.isEmpty()) {
						out.append("error").append('\n');
						sb = null;
						break;
					}

					if (reverse == 0) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}
				}
			}

			if (sb != null) {
				int len = dq.size();
				for (int i = 0; i < len; i++) {
					if (reverse == 0) {
						int e = dq.pollFirst();
						sb.append(e).append(',');
					} else {
						int e = dq.pollLast();
						sb.append(e).append(',');
					}
				}
				if (sb.length() > 1) {
					sb.delete(sb.length() - 1, sb.length());
				}
				sb.append(']');
				out.append(sb.toString()).append('\n');
			}

		}

		System.out.println(out);

	}

}
