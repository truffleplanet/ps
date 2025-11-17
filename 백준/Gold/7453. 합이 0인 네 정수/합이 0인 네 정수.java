/*
가장 단순하게 생각할 수 있는 풀이는
전부 해보는 것인데, 그러면 4000^4이 된다.

그러면 좀 덜 선택하는 방법이 뭐가 있을까? 

step 1.
a, b에서 아무거나 두개 고른다 (4000^2)
그 합을 리스트 X로 관리한다.

c, d에서 아무거나 두개 고른다 (4000^2)
그 합을 리스트 Y로 관리한다.

리스트 y를 정렬한다  (4000^2log(4000^2))

X의 값을 순회하며, Y리스트 이진탐색해서 가능한 값 전부 구한다.
가능한 최대 절대값은 2^28 * 4 --> 2^30이므로 int 범위 사용 가능하다.

그런데.. 카카오 주사위 굴리기 문제에서 해봤듯이
이 문제 역시 빈도로 접근 가능하다. 
두 방식 모두 써서 풀어보자.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N * N];
        int[] CD = new int[N * N];

        int idx = 0;
        for (int a : A) {
            for (int b : B) {
                AB[idx++] = a + b;
            }
        }

        idx = 0;
        for (int c : C) {
            for (int d : D) {
                CD[idx++] = c + d;
            }
        }

        Arrays.sort(CD);
        
        long count = 0;
        for (int ab : AB) {
            int lo = lowerBound(CD, -ab);
            int hi = upperBound(CD, -ab);
            count += hi - lo;
        }
        
        System.out.println(count);

    }

    public static int lowerBound(int[] arr, int key) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) >> 1;

            if (arr[mid] >= key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public static int upperBound(int[] arr, int key) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) >> 1;

            if (arr[mid] > key) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
    
}