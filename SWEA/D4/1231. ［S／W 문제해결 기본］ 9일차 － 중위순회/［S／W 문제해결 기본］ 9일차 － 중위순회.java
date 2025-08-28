import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static StringBuilder sb;
	static int N;
	static char[] tree;

	static void inorder(int idx) {
		if (idx > N)
			return;

		inorder(idx * 2);
		sb.append(tree[idx]);
		inorder(idx * 2 + 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new char[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				char val = st.nextToken().charAt(0);
				tree[idx] = val;
			}

			sb.append("#").append(tc).append(" ");
			inorder(1);
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
