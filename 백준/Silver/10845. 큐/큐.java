import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());

		Queue queue = new Queue();

		for (int i = 0; i < N; i++) {
			String[] line = sc.nextLine().split(" ");
			String command = line[0];

			switch (command) {
			case "push":
				int x = Integer.parseInt(line[1]);
				queue.push(x);
				break;
			case "pop":
				System.out.println(queue.pop());
				break;
			case "size":
				System.out.println(queue.size());
				break;
			case "empty":
				int output = (queue.empty()) ? 1 : 0;
				System.out.println(output);
				break;
			case "front":
				System.out.println(queue.front());
				break;
			case "back":
				System.out.println(queue.back());
				break;
			}

		}
		sc.close();
	}
}

class Queue {
	private Node head;
	private Node tail;
	private int length = 0;

	public void push(int x) {
		Node next = new Node(x);
		if (length == 0) {
			head = next;
			tail = next;
			length++;
			return;
		}
		tail.next = next;
		tail = next;
		length++;
	}

	public int pop() {
		if (this.empty()) {
			return -1;
		} else {
			int output = head.val;
			head = head.next;
			length--;
			return output;
		}
	}

	public int size() {
		return length;
	}

	public boolean empty() {
		return (length == 0) ? true : false;
	}

	public int front() {
		return (this.empty()) ? -1 : head.val;
	}

	public int back() {
		return (this.empty()) ? -1 : tail.val;
	}
}

class Node {
	int val;
	Node next;

	public Node() {

	}

	public Node(int val) {
		this.val = val;
	}

}