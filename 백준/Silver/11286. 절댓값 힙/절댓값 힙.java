import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	static class HeapNode implements Comparable<HeapNode> {
		final int v;
		final int key;

		public HeapNode(int v) {
			super();
			this.v = v;
			this.key = (v < 0) ? -v : v;
		}

		@Override
		public int compareTo(HeapNode o) {
			int v1 = Integer.compare(this.key, o.key);
			if (v1 != 0)
				return v1;
			return Integer.compare(this.v, o.v);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<HeapNode> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) {
				HeapNode u = pq.poll();
				if (u == null) {
					sb.append('0').append('\n');
				} else {
					sb.append(u.v).append('\n');
				}
			} else {
				pq.offer(new HeapNode(x));
			}
		}

		System.out.println(sb);
	}

}
