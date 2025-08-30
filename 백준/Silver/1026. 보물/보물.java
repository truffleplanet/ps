import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 정의:
 * 길이가 같은 두 백터 A, B가 주어진다. A 벡터 내의 수는 마음대로 재배열할 수 있다. 두 벡터 내적의 최솟값을 구하여라. 
 * 
 * 제약 사항:
 * 벡터의 각 원소는 100 이하의 양의 정수이다. 
 * 벡터의 길이는 50보다 작다.
 * 100*100*50
 * 
 * 풀이 아이디어:
 * B의 최솟값에 A의 최댓값을 순서대로 붙인다.
 * 혹은 B의 값이 큰 순서대로 A의 값을 붙인다. 
 * 
 * 구현 아이디어:
 * 순위를 어떻게 매길 것인가? 
 * - 사실 B는 정렬이 안되지만, A를 원하는 위치로 다 옮기는게 
 * - A, B의 조합 차원에서 B도 정렬한 것과 다를 것이 없다.
 * - 즉 최소합만 출력하면 되는 조건하에는 둘다 정렬해도 된다. 
 * 
 * 1. 양쪽 다 정렬.
 * 2. 반대로 곱하기
 * 
 * O(nlogn) // n이 최대 100으로 N으로 풀어야할 부담이 없다. 그런 부담 있으면 카운팅 sort하면 될듯.
 *  
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);
		Arrays.sort(B);

		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += A[i] * B[N - i - 1];
		}

		System.out.println(ans);
	}

}
