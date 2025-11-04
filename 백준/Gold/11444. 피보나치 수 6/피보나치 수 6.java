/*
피보나치 행렬식을 모르면 풀 수 없는 문제

1 1
1 0  행렬 사용해서 풀 수 있음.

필요한 함수
fastExp

matmul
*/


import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static long X = 1_000_000_007;

    public static long[][] matmul(long[][] a, long[][] b) {
        int N = a.length;
        int M = b[0].length;
        
        long[][] out = new long[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    out[i][j] = (out[i][j] + (a[i][k] * b[k][j])) % X;
                } 
            }
        }
        return out;
    }

    public static long[][] fastExp(long[][] base, long e) {
        long[][] result = {{1, 0}, {0, 1}}; //unit Matrix
        long[][] x = base;
        long y = e;

        while(y > 0) {
            if (y % 2 != 0) {
                result = matmul(result, x);
            }
            x = matmul(x, x);
            y = y / 2;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long[][] fib = {{1}, {0}};
        if (N < 2) {
            System.out.println(fib[1 - (int)N][0]);
            return;
        }
        
        long[][] A = {{1, 1}, {1, 0}};
        long[][] A_N = fastExp(A, N);
        long[][] ans = matmul(A_N, fib);
        System.out.println(ans[1][0]);
    }
}