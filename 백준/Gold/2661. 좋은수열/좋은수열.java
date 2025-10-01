/*
1. 
N 길이의 최소 좋은 수열을 만드는 것은 
N - 1 길이의 최소 좋은 수열에 {1, 2, 3} 중 가장 작은 숫자를 붙이는 것.
두 단계 이상 뒤로 가볼 필요가 없다. 앞에서 최소였던게 추가하는 것보다 큰 자릿수에 있어서.
그리디 백트래킹이라 해야하나..? 
그런데 혹시라도 1, 2, 3 중 무엇을 붙여도 나쁜 수열이 되어서 두 번 뒤로 가야할 수 도 있나..?

--> 귀류법:
가정: 어떤 S라는 좋은 수열에 대해 그 뒤에 1, 2, 3 중 무엇을 붙여도 나쁜 수열이 되는 경우가 존재한다.


1. S + 1가 나쁘면 S는 {a}1{a}1로 끝난다.    (a, b, c는 부분수열)
2. S + 2가 나쁘면 S는 {b}2{b}2로 끝난다.
3. S + 3가 나쁘면 S는 {c}3{c}3로 끝난다. 
뭐 모르겟다.. 이건 문제푸는데 중요하지 않으니 여기까지.

2. 
좋은 수열인지 판별하는 방법은
먼저, 수열의 길이가 k이면 최대 부분수열의 길이는 k/2이다. 
1 ~ k/2 길이까지 투 윈도우로 탐색하면 된다.

그런데 우리는 이전의 k-1 길이의 좋은 수열에서부터 시작하므로,
새로 추가된 부분을 포함해서 부분수열을 만들고 그 인접 수열만 확인하면 된다.

즉 반복해서 k/2번 비교하면 되는데
비교 각각이 길이 * 2 만큼의 시간이 소요되니깐
(1 + 2 + 3 + 4 + ... + k/2 ) * 2만큼의 시간이 소요됨.
이는 등차수열 합 공식을 생각하면 O(k^2)으로 표현 가능.

3.
그러면 추가하는 과정에서 
최악으로 잡아도
3 * (1^2 + 2^2 + 3^2 + 4^2 + 5^2 + ... + N^2) 임.
근데 sum(k^2) = n(n+1)(2n+1) / 6 (고등학교 수학) 이니깐.
대략 O(N^3) 시간 복잡도 이고, 
  80^3은 51만임. 

4. 
1번에서 이야기한 조건으로 N길이 수열 최초 발견 시 그게 최소니깐 
거기서 백트래킹 멈추면 됨. 
boolean을 리턴값으로 true 전파해서 탐색 끝내자.
*/

import java.util.Scanner;

public class Main {

	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();

		backtrack(new StringBuilder());
	}

	public static boolean backtrack(StringBuilder k) {
		if (k.length() == N) {
			System.out.println(k.toString());
			return true;
		}

		for (int i = 1; i <= 3; i++) {
			k.append(i);
			if (isGood(k)) {
				if (backtrack(k))
					return true;
			}
			k.deleteCharAt(k.length() - 1);
		}
		return false;
	}

	public static boolean isGood(StringBuilder k) {
		int K = k.length();
		int R = K / 2;

		for (int s = 1; s <= R; s++) {
			boolean isMatch = true;
			for (int c = 0; c < s; c++) {
				int i = K - 1 - c;
				int j = i - s;
				if (k.charAt(i) != k.charAt(j)) {
					isMatch = false;
					break;
				}
			}
			if (isMatch) {
				return false;
			}
		}
		return true;
	}
}