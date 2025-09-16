
// 1,524,157,875,019,052,100
// 9,223,372,036,854,775,808
// 1,000,000,000,000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static final long P = 1_234_567_891;

	static long factorial(long x) {
		long out = 1;
		for (int i = 2; i <= x; i++) {
			out *= i % P;
		}
		return out;
	}

	static long pow(long base, long e) {
		if (e == 0) {
			return 1;
		}

		long half = pow(base, e / 2) % P;

		if (e % 2 == 0) {
			return (half * half) % P;
		} else {
			return (((half * half) % P) * (base % P)) % P;
		}
	}

	static long comb(long n, long r) {

		long top = 1;
		for (long i = n; i > n - r; i--) {
			top = (top * i) % P;
		}
		long bottom = 1;
		for (long i = 2; i <= r; i++) {
			bottom = (bottom * i) % P;
		}

		bottom = pow(bottom, P - 2) % P;
		return (top * bottom) % P;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			long n = Integer.parseInt(st.nextToken());
			long r = Integer.parseInt(st.nextToken());
			sb.append('#').append(tc).append(' ').append(comb(n, r)).append('\n');
		}

		System.out.println(sb);

	}

}
