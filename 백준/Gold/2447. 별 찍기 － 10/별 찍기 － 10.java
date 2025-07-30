import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 재귀적인 패턴임.
		// 
		/*
		 *  base case
		 *  ***
		 *  * *
		 *  ***
		 *  
		 *  average case
		 *  F(n-1) + F(n-1) + F(N-1)
		 *  F(n-1) + space + F(N-1)
		 *  F(n-1) + F(n-1) + F(N-1)
		 *  
		 *  // 방법 1
		 *  2차원 배열의 값들로 생각하고
		 *  2차원 배열 선언해서 만들기
		 *  
		 *  // 방법 2.
		 *  StringBuilder 활용하기
		 *  
		 */
		Scanner sc = new Scanner(System.in);
		final int n = sc.nextInt();
		sc.close();
		
		StringBuilder sb = new StringBuilder();
		int[][] matrix = recursion(n);
		for (int[] line : matrix) {
			for (int val : line) {
				sb.append((val == 0) ? " " : "*");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int[][] recursion(int n) {
		if (n == 3)
			return new int[][]{{1, 1, 1},{1,0, 1},{1, 1, 1}};
		
		int third = n / 3;
		int[][] smaller = recursion(third);
		int[][] matrix = new int[n][n];
		// 0 ~ n/3, n/3 ~ 2n/3, 2n/3 ~ end;
		// 첫줄
		for (int i = 0; i < third; i++) {
			for (int j = 0; j < third; j++) {
				matrix[i][j] = smaller[i][j];
			}
		}
		for (int i = 0; i < third; i++) {
			for (int j = third, jdx=0; j < 2 * third; j++, jdx++) {
				matrix[i][j] = smaller[i][jdx];
			}
		}
		for (int i = 0; i < third; i++) {
			for (int j = 2 * third, jdx=0; j < n; j++, jdx++) {
				matrix[i][j] = smaller[i][jdx];
			}
		}
		// 2번째 줄
		for (int i = third, idx=0; i < 2 * third; i++, idx++) {
			for (int j = 0; j < third; j++) {
				matrix[i][j] = smaller[idx][j];
			}
		}
		for (int i = third, idx=0; i < 2 * third; i++, idx++) {
			for (int j = third; j < 2 * third; j++) {
				matrix[i][j] = 0;
			}
		}
		for (int i = third, idx=0; i < 2 * third; i++, idx++) {
			for (int j = 2 * third, jdx=0; j < n; j++, jdx++) {
				matrix[i][j] = smaller[idx][jdx];
			}
		}
		
		//3 번째 줄
		for (int i = third * 2, idx=0; i < n; i++, idx++) {
			for (int j = 0; j < third; j++) {
				matrix[i][j] = smaller[idx][j];
			}
		}
		for (int i = third * 2, idx=0; i < n; i++, idx++) {
			for (int j = third, jdx=0; j < 2 * third; j++, jdx++) {
				matrix[i][j] = smaller[idx][jdx];
			}
		}
		for (int i = third * 2, idx=0; i < n; i++, idx++) {
			for (int j = 2 * third, jdx=0; j < n; j++, jdx++) {
				matrix[i][j] = smaller[idx][jdx];
			}
		}
		
		return matrix;
		
	}
}

