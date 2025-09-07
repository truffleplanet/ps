import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 불변: 
 * 1. 스택의 요소는 오름차순으로 삽입되어야 한다. 
 * 2. 수열의 요소의 값은 중복이 없다.
 * 3. 수열의 값을 차례대로 채워야 한다. 
 * 4. 내림차순인 부분 수열에서, a(i), a(i+1) 사이의 값은 이미 수열에 다 등장한 적이 있어야 한다.
 * 
 * 문제 정의:
 * 1~N 까지의 수를 스택에 차례대로 push할 수 있다. pop은 push 사이에 자유롭게 하여도 된다.
 * pop을 하여 목표 수열을 만들어야 한다. 즉 pop한 수는 목표 수열의 요소로 차례대로 포함된다.
 * 수열의 요소 값은 1~N 범위를 벗어나지 않는다. 
 * 
 * (1) 주어진 수열을 만들 수 있는지 확인하라 
 * (2) 만들 수 있다면 pop, push 연산을 어떤 순서로 수행해야 하는지 작성하라
 * 
 * 
 * 풀이: O(N)
 * - 시작: 
 * stack에 1을 push한다.
 * stack의 다음 값을 가리키는 변수 c = 2을 초기화한다.
 * 목표 수열의 요소 인덱스를 표시할 k = 0으로 두기.
 * - 반복:
 * 목표 수열의 다음 요소 값 seq[k]
 * stack의 맨 윗값보다 작다면, 끝.
 * stack의 값보다 크다면 스택의 다음값 푸쉬.
 * stack의 값과 같다면 pop.목표 k++;
 * 
 * 만약 k == n 이면 끝. (0-based index로 마지막 요소는 k = n-1일 것)
 * 
 * 
 * 효율성 증명: 
 * 숫자 전체를 순회도 안해보고, 가능성 판단할 수 있는가? X. 현재 입력 규칙에 의하면 이분탐색이 가능한 것도 아니고.. 뭐 없다.
 * 따라서 최적은 순회에 드는 최소 비용인 O(N)보다 낮을 수 없다. 
 * 현재 풀이는 최적 풀이라고 할 수 있다.
 * 
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(0);// null 방지
		int next = 1;

		for (int i = 0; i < N; i++) {
			int target = Integer.parseInt(br.readLine());

			if (stack.peek() > target) {
				System.out.println("NO");
				return;
			}

			// 큰값이면 단지 더 채우면 됨. 그거 여기서 하자 .
			// 지역 최대값이 target으로 들어와도 next는 단조성 있으므로 그냥 넘어가짐.
			if (stack.peek() < target) {
				while (next <= target) {
					stack.push(next);
					next++;
					sb.append('+').append('\n');
				}
			}

			if (stack.peek() == target) {
				stack.pop();
				sb.append('-').append('\n');
			}
		}

		System.out.println(sb);

	}

}
