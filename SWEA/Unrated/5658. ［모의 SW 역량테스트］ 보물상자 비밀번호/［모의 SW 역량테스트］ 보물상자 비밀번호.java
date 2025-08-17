/*
 * quat = n / 4;
 * 그리고 quat - 1 만큼의 회전을 하면 모든 가능한 경우를 알 수 있음.
 * 
 * 
 * 회전은 그냥 arr의 인덱스 밀기로 도전. 
 * linked list나 머 이런저런 방법이 있겠지만..
 * 잘 생각해보면, quat만큼의 슬라이딩 윈도우 밀면서 모든 값 확인하면 됨. 
 * 근데 그걸 회전을 고려하니 모든 위치에서 하는 것임. 
 * [i % N] i = x + (0 to quat);
 * 
 * 그 이후에는 
 * 모든 경우를 중복없이 정렬해야 함. 
 * 방법 1. 
 *  N크기 배열 선언 후 
 *  모든 가능한 값을 배열에 저장한다.
 *  배열을 정렬 한 후, 순회하며 중복값을 제외하고 K번째 순서의 값을 찾는다. 
 *  
 *  방법 2.
 *  값을 set에 저장하여 중복 없이 관리하고, 
 *  정렬된 리스트로 변환한 후 k번째 값을 찾는다. 
 *  
 *  
 * 방법 2가 심플하지만, 이번엔 방법 1로 해보기로 함.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

//		System.out.println(Math.pow(16, 7) > Integer.MAX_VALUE); 최대값은 16^7 - 1 이므로 int 사용 가

		int[] seq = new int[28];
		int[] vals = new int[28]; // 슬라이딩을 하는데.. 회전해서도 해야함. 그말은 모든 위치에서 한다는 뜻임.

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int quat = N / 4;

			String strSeq = br.readLine();

			for (int i = 0; i < N; i++) {
				seq[i] = Character.digit(strSeq.charAt(i), 16);
			}

			int e = (int) Math.pow(16, quat - 1); // 중복 연산 줄이기
			for (int x = 0; x < N; x++) {
				int sum = 0;
				int digit = e;
				for (int i = 0; i < quat; i++) { // 10진수 변환
					int idx = (x + i) % N;
					sum += seq[idx] * digit;
					digit /= 16;
				}
				vals[x] = sum;
			}

			Arrays.sort(vals, 0, N);
			int prev = -1;
			int rank = 0;
			for (int i = N - 1; i >= 0; i--) { // 정답 찾기
				if (vals[i] == prev)
					continue;
				rank++;
				prev = vals[i];
				if (rank == M) {
					break;
				}
			}

			sb.append("#").append(tc).append(" ").append(prev).append("\n");

		}

		System.out.println(sb);

	}

}
