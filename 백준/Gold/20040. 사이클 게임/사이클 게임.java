import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;

	static int find(int x) {
		if (x == parents[x])
			return x;

		parents[x] = find(parents[x]);
		return parents[x];
	}

	static boolean union(int x, int y) {
		int x_root = find(x);
		int y_root = find(y);

		if (x_root == y_root)
			return false;

		parents[x_root] = y_root;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		// make set
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}

		int cnt = 0;
		boolean flag = true;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (union(u, v)) {
				cnt++;
			} else {
				flag = false;
				cnt++;
				break;
			}
		}

		if (flag) {
			System.out.println(0);
		} else {
			System.out.println(cnt);
		}

	}

}
