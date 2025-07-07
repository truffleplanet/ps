import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		String[] tokens = sc.nextLine().split(" ");
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(tokens[i]);
			list.add(list.size()-num, i+1);
		}
		for (int val : list) {
			System.out.print(val + " ");
	}
	}
}
