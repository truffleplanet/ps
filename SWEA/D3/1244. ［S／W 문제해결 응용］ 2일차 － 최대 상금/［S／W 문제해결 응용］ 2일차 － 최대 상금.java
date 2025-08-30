import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 문제 정의:
 * 입력받은 십진수 숫자 N에서 두 숫자의 위치를 k번 교환해야 한다. 
 * 교환 후 가장 큰 숫자가 남도록 교환해라. 
 * 
 * 제약 조건:
 * N의 최대 자릿수는 6
 * k는 최대 10
 * 교환은 반드시 k번을 해야 한다. 교환을 안 할수는 없음.
 * Java의 경우 20초/ 테케 15
 * 
 * 
 * 브레인스토밍:
 * 큰 자리에 큰 숫자를 두고, 작은 자리에 작은 숫자를 두고 싶지만,, 
 * swap이 원하는 대로 정렬할 수 있는 건 아님. 특히 swap을 k번 반드시 해야하니깐.. 
 * [1, 9, 1] 인데 2번 swap 해야하면  [1, 1, 9] -> [9, 1, 1] 이 최선임. 
 * 만약 큰 것을 무조건 앞으로 끌어오려하면 [9, 1, 1] -> [1, 9, 1] 로 최적해가 나오지 않는다.
 * 
 * 
 * 귀찮은데 완탐의 시간복잡도는?
 * (6C2)^10 * 15(테케)임. 
 * 8,649,755,859,375
 * 8조는 20초가 와도 감당 못한다..
 * 
 * 
 * 가지치기 아이디어
 * - 내림차순 정렬한게 최댓값인데, 항상 가능한 건 아님. 일단 종료조건으로 사용 가능함. 
 * 
 * 
 * 작은 케이스부터 생각해보자.
 * - 남은 교환이 0회이면 반환해야한다.
 * - 남은 교환이 1회이면 그리디한 선택을 하면 된다.
 * - 남은 교환이 2회이면 여기부터는 고민이 필요하다. 
 * 
 * 어떤 교환횟수에서도 강건한 탐색 방법이 무엇일까? 꼭 선형으로 탐색하길 바라는게 아니라..
 * 완전 탐색을 피할 수 있는 방법.. 
 * 
 * 
 * 
 * 혹시 이런 방법은 어떨까? 
 * 내림차순으로 정렬하고, 그거보다 하나 작은 수, 그거보다 더 작은 수.. 이렇게 
 * 최대 수 후보를 열개쯤 만들고
 * 그중에 만들 수 있는 걸 찾는거지. 근데 스왑이란 방식의 복잡성이 그걸 가로막는다. 
 * 
 * 3 2 8 8 8 
 * k번 교환으로 만들 수 있는 수의 집합을 전부 찾기로 문제를 변환해보자.
 * 
 * 1. 같은 수를 교환하면 새로운 수가 되지 않는다. (당연하지만..)
 * 2. 근데 다 다른 수면 어쩌려고 이런 방법을 씀.? 
 * 3. 미쳤다 미쳤어..
 * 
 * !:
 * 숫자가 최대 6개이고, 6개가 전부 다르다고 해도 
 * 가능한 쌍은 6!으로 720개임. 
 * 즉, 각 교환횟수별로 이미 나온 수열은 등장하지 않게 한다면
 * 
 * 
 */

public class Solution {

	static int bfs(String s, int k) {
		int n = s.length();
		Set<String> curr = new HashSet<>();
		curr.add(s);

		for (int d = 0; d < k; d++) {
			Set<String> next = new HashSet<String>();
			for (String now : curr) {
				char[] arr = now.toCharArray();
				for (int i = 0; i < n - 1; i++) {
					for (int j = i + 1; j < n; j++) {
						swap(arr, i, j);
						next.add(new String(arr));
						swap(arr, i, j);
					}
				}
			}
			curr = next;
		}

		int best = 0;
		for (String t : curr) {
			int val = Integer.parseInt(t);
			best = Math.max(val, best);
		}
		return best;
	}

	static void swap(char[] a, int i, int j) {
		char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int k = Integer.parseInt(st.nextToken());

			sb.append("#").append(tc).append(" ").append(bfs(s, k)).append("\n");
		}
		System.out.println(sb);
	}

}
