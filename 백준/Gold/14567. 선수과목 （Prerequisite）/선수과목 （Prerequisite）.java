import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 정의:
 * 모든 전공 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 걸리는지 계산하라. 
 * 
 * 제약 조건:
 * 모든 과목은 매 학기 항상 개설된다.
 * 한 학기에 들을 수 있는 과목 수에는 제한이 없다.
 * A < B인 입력만 주어진다 -> 사이클이 없다.
 * 방향 그래프이다. 
 * 
 * 
 * 예제 생각해보기 
 * 2 -> 3
 * 1 -> 2
 * 
 * 과목 1은 선수 과목이 없으므로 1번째 학기에 수강 가능
 * 과목 2 그다음 학기에 듣고 
 * 과목 3 그다음 학기에 들어서 
 * 총 3학기가 소요된다. 
 * 
 * 과목 6개, 관계 4개 
 * 1 -> 2
 * 1 -> 3
 * 2 -> 5
 * 4 -> 5
 * 
 * 1학기  1, 4듣고
 * 2학기  2, 3듣고
 * 3학기 5듣고  
 * 1 2 2 1 3 1
 * 
 * 예제 입력 2의 교훈 
 * 선수 과목 표시 자체가 없는 과목도 있다. 해당 과목은 당연히 첫 학기에 이수가 가능하다.
 * 
 * 구현 아이디어:
 * 위상정렬.
 * while(모든 노드를 방문시까지)
 * 	들어오는 간선이 없는 노드들 전부 기록, 해당 노드들에서 나가는 간선 전부 제거 
 * 	학기 기록
 * 
 * 디테일:
 * 위상 정렬에 자신감이 없네.
 * 일단 아이디어에 맞게, 들어오는 간선을 노드별로 세어두고
 * 매번 전체 노드 순회하면서 하는 brute force 구현 해보자. 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int[] count = new int[V + 1];
		List<List<Integer>> G = new ArrayList<>();
		for (int i = 0; i < V + 1; i++) {
			G.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			count[v]++;
			G.get(u).add(v);
		}

		int[] ans = new int[V + 1];
		int ith = 1;
		int nodeCnt = 0;
		List<Integer> curr = new ArrayList<>();
		while (nodeCnt < V) {
			curr.clear();
			for (int i = 1; i < V + 1; i++) {
				if (count[i] == 0) {
					count[i] = -1;
					nodeCnt++;
					ans[i] = ith;
					curr.add(i);
				}
			}

			for (int u : curr) {
				for (int v : G.get(u)) {
					count[v]--;
				}
			}

			ith++;
		}

		for (int i = 1; i < V + 1; i++) {
			sb.append(ans[i]).append(" ");
		}

		System.out.println(sb);

	}

}
