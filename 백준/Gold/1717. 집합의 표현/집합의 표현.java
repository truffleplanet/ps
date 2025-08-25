import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	final static int CMD_UNION = 0;
	final static int CMD_ISUNION = 1;

	public static class DisjointSet {

		int range;
		int[] parents;

		public DisjointSet(int range) {
			super();
			this.range = range;
			parents = new int[range + 1];
			for (int i = 0; i <= range; i++) {
				parents[i] = i;
			}
		}

		private int find(int x) {
			if (x == parents[x])
				return x;
			parents[x] = find(parents[x]); // path compression
			return parents[x];
		}

		public void union(int x, int y) {
			int x_Root = find(x);
			int y_Root = find(y);
			if (x_Root == y_Root)
				return;
			parents[x_Root] = y_Root;
		}

		public boolean isUnion(int x, int y) {
			int x_Root = find(x);
			int y_Root = find(y);
			if (x_Root == y_Root)
				return true;
			else
				return false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 입력
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		DisjointSet d = new DisjointSet(N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case CMD_UNION:
				d.union(x, y);
				break;
			case CMD_ISUNION:
				if (d.isUnion(x, y))
					sb.append("YES");
				else
					sb.append("NO");

				sb.append("\n");
				break;
			}
		}

		System.out.println(sb);

	}

}
