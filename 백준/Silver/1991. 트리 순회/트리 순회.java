import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Map<String, Node> tree = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			String v = tokens[0];
			String left = tokens[1];
			String right = tokens[2];

			tree.putIfAbsent(v, new Node(v));
			Node parent = tree.get(v);

			if (!left.equals(".")) {
				tree.putIfAbsent(left, new Node(left));
				parent.left = tree.get(left);
			}

			if (!right.equals(".")) {
				tree.putIfAbsent(right, new Node(right));
				parent.right = tree.get(right);
			}
		}

		preorder(tree.get("A"));
		System.out.println();
		inorder(tree.get("A"));
		System.out.println();
		postorder(tree.get("A"));

	}

	public static void preorder(Node v) {
		if (v == null) {
			return;
		}
		System.out.print(v.self);
		preorder(v.left);
		preorder(v.right);
	}

	public static void inorder(Node v) {
		if (v == null) {
			return;
		}
		inorder(v.left);
		System.out.print(v.self);
		inorder(v.right);
	}

	public static void postorder(Node v) {
		if (v == null) {
			return;
		}
		postorder(v.left);
		postorder(v.right);
		System.out.print(v.self);
	}

}

class Node {
	String self;
	Node left = null;
	Node right = null;

	public Node(String self) {
		this.self = self;
	}

}
