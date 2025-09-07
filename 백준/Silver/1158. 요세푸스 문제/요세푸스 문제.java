import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 계속 카운팅을 멈추지 않을 수 있으면 된다. 
 * 
 * 환형 링크드 리스트? 
 * Queue
 * 
 * 
 * 풀이:
 * 큐에서 숫자를 세며 뽑는다.
 * x = dequeue 
 * cnt++
 * cnt < K 면 
 *  enqueue (x)
 * else 
 * 	sb.append(x);
 *   
 * 
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}

		int cnt = 0;
		sb.append('<');
		while (!q.isEmpty()) {
			int x = q.poll();
			cnt++;

			if (cnt == K) {
				cnt = 0;
				sb.append(x).append(',').append(' ');
			} else {
				q.offer(x);
			}
		}

		sb.delete(sb.length() - 2, sb.length());
		sb.append('>');
		System.out.println(sb);
	}
}
