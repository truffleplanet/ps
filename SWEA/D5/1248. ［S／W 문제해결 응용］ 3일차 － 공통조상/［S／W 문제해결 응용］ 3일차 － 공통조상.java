import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int size;

	static class Node {
		Node parent;
		Node left;
		Node right;
		int id;

		public Node(int id) {
			super();
			this.id = id;
		}

	}

	static void inorder(Node x) {
		if (x == null) {
			return;
		}
		inorder(x.left);
		size++;
		inorder(x.right);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Node[] nodes = new Node[V + 1];
			for (int i = 1; i <= V; i++) {
				nodes[i] = new Node(i);
			}

			// 간선 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				Node parent = nodes[u];
				Node child = nodes[v];

				if (parent.left == null) {
					parent.left = child;
				} else {
					parent.right = child;
				}
				child.parent = parent;
			}

			Node A = nodes[a];
			Node B = nodes[b];

			Node p_A = A.parent;
			Node p_B = B.parent;

			ArrayList<Integer> a_parents = new ArrayList<>();
			ArrayList<Integer> b_parents = new ArrayList<>();

			while (p_A != null) {
				a_parents.add(p_A.id);
				p_A = p_A.parent;
			}
			while (p_B != null) {
				b_parents.add(p_B.id);
				p_B = p_B.parent;
			}

//			System.out.println("a 부모: " + a_parents);
//			System.out.println("b 부모: " + b_parents);
			int root = 1;
			for (int i = 0; i < a_parents.size(); i++) {
				if (root != 1)
					break;
				for (int j = 0; j < b_parents.size(); j++) {
//					System.out.println("a: " + a_parents.get(i) + ", b: " + b_parents.get(j));
					if (a_parents.get(i).equals(b_parents.get(j))) {
						root = a_parents.get(i);
						break;
					}
				}
			}
//			System.out.println("공통 루트: " + root);
			size = 0;
			inorder(nodes[root]);
			sb.append("#").append(tc).append(" ").append(root).append(" ").append(size).append("\n");
		}

		System.out.println(sb);
	}
}
