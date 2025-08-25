import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 여행 경로가 가능한가? 
 * 단지 연결되어 있는지만 확인하는 문제임.
 * 
 * 즉 edge들의 연결에 따라 
 * node들을 disjoint set으로 관리할 때, 
 * 여행 경로는 하나의 집합에 들어오는지 확인하면 됨. 
 * 
 * 도시의 번호는 1 ~ N까지 차례대로 매겨져 있으므로 
 * static array 기반의 parents 표현이 가능함.
 */

public class Main {
	final static int CONNECTED = 1;

	static int find(int[] parents, int x) {
		if (parents[x] == x)
			return x;

		parents[x] = find(parents, parents[x]);
		return parents[x];
	}

	static void union(int[] parents, int x, int y) {
		int x_root = find(parents, x);
		int y_root = find(parents, y);

		if (x_root == y_root)
			return;

		parents[x_root] = y_root;
	}

	static boolean isUnion(int[] parents, int x, int y) {
		int x_root = find(parents, x);
		int y_root = find(parents, y);

		if (x_root == y_root)
			return true;

		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[] parents = new int[N + 1]; // 1-based index
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		// 입력이 adjacency matrix로 들어옴.
		// O(N^2) ?? 아마 union이 path 압축 했을 때 상수시간에 끝나는지 아닌지 모르겠음.
		// 어쨌든 순회만 해도 squared time.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int matrix_ij = Integer.parseInt(st.nextToken());
				if (matrix_ij == 1) {
					union(parents, i + 1, j + 1); // 행렬은 0-based idx로 들어옴.
				}
			}
		}

		// 이제 목표 경로가 연결되어있나 확인하기 O(N)
		st = new StringTokenizer(br.readLine());
		int u = Integer.parseInt(st.nextToken());
		for (int i = 1; i < M; i++) {
			int v = Integer.parseInt(st.nextToken());
			if (isUnion(parents, u, v)) {
				u = v;
				continue;
			} else {
				System.out.println("NO");
				return;// 프로그램 종료
			}
		}
		System.out.println("YES");

	}

}
