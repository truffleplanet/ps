/*
제곱수로 나누어떨어지지 않는다의 의미는?
k <= root(n) < k + 1 일 때 
n이 2^2, 3^2, ... , k^2으로 나누어 떨어지지 않는다.


문제 정의:
[min, max] 범위의 제곱 ㄴㄴ수 개수를 출력하라.

제약 조건:
min의 범위는 1 ≤ min ≤ 1,000,000,000,000
max의 범위는 min ≤ max ≤ min + 1,000,000
즉 최대 구간은 1,000,000



풀이:
아주 대충생각하면, min최대일 때, root(n)은 100만이고,
제곱 ㄴㄴ수인지 판단하는 것은 매 회가 100만걸림.
그러면 100만 * 100만이니 시간 복잡도 당연히 초과함.
그러니 효율화를 해봐야함.

음.. 일단 위에서는 k를 root(n) 버림으로 잡았는데, 
생각해보면 제곱수로 나누어 떨어지지 않는다는 것은..


2^2, 4^2, 8^2, 16^2
을 생각해보면, 16^2으로 나누어떨어지면 당연히 8^2으로도 나누어떨어지고 4^2으로도 나누어떨어지고 2^2으로도 나누어떨어진다.
10^2으로 나누어떨어지면 5^2과 2^2으로도 나누어떨어진다.
이러한 관계를 이용하여 제곱 ㄴㄴ수인지 확인하는 연산량을 줄일수 있을듯하다.



[min, max]범위에서 알짜 제곱수들..만 어떻게 뽑을까? 
일단 root(max)를 시작점으로 두고..
root(max)음

[1,k]까지의 정수는 소수 아니면 합성수인데 합성수는 소수의 곱으로 표현이 가능하다 
따라서 [1,k]의 수의 제곱수는 [1,k] 소수의 제곱수의 곱으로 표현할 수 있다. 
따라서 소수 제곱수의 배수만 채로 지워내면 된다.

그러면.. 최대 100범위 내 소수로 
각 칸들 지우는건.. 100만범위에서 
(100만 / 4) + (100만 / 9)  + (100만 / 25) + (100만 / 36) + ... 인데
이게 얼마 걸리는지 계산은 잘 못하겠지만 100만 * 80000을 쌩으로 하는 것보다는 훨씬 빠른게 자명하다. 

-- 그냥 제곱수의 역수 합도 무한까지 더해봤자 1.6에 근사한다고 한다.
-- 우리는 소수만 특정범위에서 보니 훨씬 작을 것이고, 거기에다가 100만 곱하는 거니깐 거의 선형시간에 수렴할듯하다.

ㄱ


*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());

		int sqrtMax = (int) Math.sqrt(max);
		int range = (int) (max - min) + 1;
		boolean[] isPrime = new boolean[sqrtMax + 1];
		boolean[] isNotNoNo = new boolean[range];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		int sqrtsqrt = (int) Math.sqrt(sqrtMax);
		for (int i = 2; i <= sqrtMax; i++) {
			if (isPrime[i]) {
				// 1. 제곱 ㄴㄴ 수가 아닌 것 찾기
				long square = (long) i * i;
				long start = (min / square) * square;
				if (start < min) {
					start += square;
				}
				for (long k = start; k <= max; k += square) {
					int key = (int) (k - min);
					isNotNoNo[key] = true;
				}

				// 2. 에라토스테네스의 채
				if (i <= sqrtsqrt) {
					for (int j = i * i; j <= sqrtMax; j += i) {
						isPrime[j] = false;
					}
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < range; i++) {
			if (!isNotNoNo[i])
				ans++;
		}

		System.out.println(ans);

	}
}