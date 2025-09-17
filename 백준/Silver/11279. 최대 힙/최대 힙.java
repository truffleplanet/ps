import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

		for (int i = 0; i < N; i++) {
			int v = Integer.parseInt(br.readLine());

			if (v == 0) {
				if (pq.isEmpty()) {
					sb.append('0').append('\n');
				} else {
					sb.append(pq.poll()).append('\n');
				}
			} else {
				pq.offer(v);
			}
		}
		System.out.println(sb);
	}

}
