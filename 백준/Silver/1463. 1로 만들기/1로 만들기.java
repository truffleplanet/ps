/*
 * 
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Node {
		int u;
		int d;

		public Node(int u, int d) {
			super();
			this.u = u;
			this.d = d;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		int cnt = 0;

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(N, 0));
		dist[N] = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int u = node.u;
			int nd = node.d + 1;

			if (u == 1) {
				System.out.println(node.d);
				return;
			}

			int v1 = u - 1;
			if (dist[v1] > nd) {
				dist[v1] = nd;
				q.offer(new Node(v1, nd));
			}

			if (u % 2 == 0) {
				int v2 = u / 2;
				if (dist[v2] > nd) {
					dist[v2] = nd;
					q.offer(new Node(v2, nd));
				}
			}

			if (u % 3 == 0) {
				int v3 = u / 3;
				if (dist[v3] > nd) {
					dist[v3] = nd;
					q.offer(new Node(v3, nd));
				}
			}

		}

	}

}
