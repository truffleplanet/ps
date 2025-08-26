import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 어떤 자석을 회전 시키면
 * 인접 자석은, 
 * - 만약 자성이 같으면 회전하지 않고.
 * - 자성이 다르다면, 어떤 자석과 반대 방향으로 회전한다.
 * 
 * - 특이 사항
 * 모든 회전은 동시에 일어난다. 순차적으로 일어나는 것이 아님.
 * 따라서 초기에 연결인지 아닌지 판단이 됨.
 * 
 * 자석 각각을 circulary linked list로 
 * 다만 doubly로 해야지 양방향 회전이 편하겠다.
 * - 필드에서 
 * - left는 head - 2
 * - right는 head + 2  
 * 
 * 값은 N극 0, S극 1
 * LinkedList[] a
 * a[0].right ^ a[1].left == 1 이면 연결, 아니면 끊김.
 * 
 *  
 */

public class Solution {

	// linked list 구현
	static class DoublyLinkedList {

		static class Node {
			int val;
			Node next;
			Node prev;

			private Node(int val) {
				this.val = val;
			}
		} // end of Node impl

		Node head;
		Node tail;

		public DoublyLinkedList() {

		}

		public void addLast(int val) {
			Node n = new Node(val);
			if (tail == null) {
				head = tail = n;
				head.next = tail;
				head.prev = tail;
			} else {
				n.prev = tail;
				n.next = head;
				tail.next = n;
				head.prev = n;
				tail = n;
			}
		}

		public void rotate(int d) {
			if (d == 1) {
				head = head.prev;
				tail = tail.prev;
			} else if (d == -1) {
				head = head.next;
				tail = tail.next;
			}
		}

		public int getHead() {
			return head.val;
		}

		public int getRight() {
			return head.next.next.val;
		}

		public int getLeft() {
			return tail.prev.val;
		}
	} // end of dll impl

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// T 입력
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			DoublyLinkedList[] magnetList = new DoublyLinkedList[4];

			for (int i = 0; i < 4; i++) {
				magnetList[i] = new DoublyLinkedList();
			}

			int K = Integer.parseInt(br.readLine());
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnetList[i].addLast(Integer.parseInt(st.nextToken()));
				}
			} // 자석 입력 완료

			// 회전 명령 입력
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int t = Integer.parseInt(st.nextToken()) - 1; // 0-based idx 로 전환
				int dir = Integer.parseInt(st.nextToken());

				int[][] isSelected = new int[4][2];
				isSelected[t][0] = 1;
				isSelected[t][1] = dir;

				int rt1 = t;
				int rt2 = t + 1;
				while (rt2 < 4) {
					int l = magnetList[rt1].getRight();
					int r = magnetList[rt2].getLeft();
					if ((l ^ r) == 1) {
						isSelected[rt2][0] = 1;
						isSelected[rt2][1] = -isSelected[rt1][1];
						rt1++;
						rt2++;
					} else {
						break;
					}
				}

				int lt1 = t;
				int lt2 = t - 1;
				while (lt2 >= 0) {
					int r = magnetList[lt1].getLeft();
					int l = magnetList[lt2].getRight();
					if ((r ^ l) == 1) {
						isSelected[lt2][0] = 1;
						isSelected[lt2][1] = -isSelected[lt1][1];
						lt1--;
						lt2--;
					} else {
						break;
					}
				}

				for (int p = 0; p < 4; p++) {
					if (isSelected[p][0] == 1) {
						magnetList[p].rotate(isSelected[p][1]);
					}
				}
			}

			int ans = 0;
			for (int p = 0; p < 4; p++) {
				if (magnetList[p].getHead() == 1)
					ans += (1 << p);
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);
	}
}