import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NavigableSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static int REG_CNT;

	static class TreeNode implements Comparable<TreeNode> {
		final int v;
		final int regNo;

		public TreeNode(int v) {
			super();
			this.v = v;
			this.regNo = REG_CNT;
			REG_CNT++;
		}

		@Override
		public int compareTo(TreeNode o) {
			int v1 = Integer.compare(this.v, o.v);
			if (v1 != 0)
				return v1;
			return Integer.compare(this.regNo, o.regNo);
		}

		@Override
		public String toString() {
			return "TreeNode [v=" + v + ", regNo=" + regNo + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			REG_CNT = 0;
			NavigableSet<TreeNode> treeset = new TreeSet<>();
			int K = Integer.parseInt(br.readLine());

			for (int $ = 0; $ < K; $++) {
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int v = Integer.parseInt(st.nextToken());

				switch (cmd) {
				case 'I':
					treeset.add(new TreeNode(v));
					break;
				case 'D':
					if (v == 1) {
						treeset.pollLast();
					} else if (v == -1) {
						treeset.pollFirst();
					}
					break;
				}

			}

			if (treeset.isEmpty()) {
				sb.append("EMPTY").append('\n');
			} else {
				sb.append(treeset.last().v).append(' ').append(treeset.first().v).append('\n');
			}

		}
		System.out.println(sb);
	}

}
