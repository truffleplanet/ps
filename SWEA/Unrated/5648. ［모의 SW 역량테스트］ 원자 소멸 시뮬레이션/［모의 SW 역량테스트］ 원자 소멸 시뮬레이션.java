import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static class Atom {
		int x;
		int y;
		int d;
		int k;

		Atom(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}
	}

	static final int[] DX = { 0, 0, -1, 1 };
	static final int[] DY = { 1, -1, 0, 0 };

	static final int END = 2000;

	static int keyOf(int x, int y) {
		return ((x + 2000) << 12) | (y + 2000);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			List<Atom> atoms = new ArrayList<>(N);

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				atoms.add(new Atom(x * 2, y * 2, d, k));
			}

			int total = 0;

			for (int tick = 0; tick <= 4000 && !atoms.isEmpty(); tick++) {

				for (Atom a : atoms) {
					a.x += DX[a.d];
					a.y += DY[a.d];
				}

				Map<Integer, int[]> map = new HashMap<>(atoms.size() * 2);
				for (Atom a : atoms) {
					if (a.x < -END || a.x > END || a.y < -END || a.y > END)
						continue;
					int key = keyOf(a.x, a.y);
					int[] v = map.get(key);
					if (v == null) {
						map.put(key, new int[] { a.k, 1 });
					} else {
						v[0] += a.k;
						v[1] += 1;
					}
				}

				if (map.isEmpty()) {
					atoms.clear();
					break;
				}

				int gained = 0;
				Set<Integer> boom = new HashSet<>(map.size() / 2 + 1);
				for (Map.Entry<Integer, int[]> e : map.entrySet()) {
					int[] v = e.getValue();
					if (v[1] >= 2) {
						gained += v[0];
						boom.add(e.getKey());
					}
				}
				total += gained;

				List<Atom> next = new ArrayList<>(atoms.size());
				if (gained == 0) {
					for (Atom a : atoms) {
						if (a.x < -END || a.x > END || a.y < -END || a.y > END)
							continue;
						next.add(a);
					}
				} else {
					for (Atom a : atoms) {
						if (a.x < -END || a.x > END || a.y < -END || a.y > END)
							continue;
						if (boom.contains(keyOf(a.x, a.y)))
							continue;
						next.add(a);
					}
				}
				atoms = next;
			}

			sb.append('#').append(tc).append(' ').append(total).append('\n');
		}

		System.out.print(sb);
	}
}