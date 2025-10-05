/*
함수를 만드는데 
N = small prime + big prime 하면 되는데
전부 안되면  Goldbach's conjecture is wrong.을 출력하면 됨.

구현1. 에라토스테네스의 채로 소수 리스트 만들기 
구현2. N이 들어오면 이진탐색으로, N미만의 가장 큰 위치 j 찾기
-- 자바 collections가 lowerbound이니, 그거보다 하나 작은 인덱스 쓰면됨
구현3. n - primes[j]가 소수인지 확인하기
맞으면 이게 가장 먼 소수 쌍
*/


import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX = 1_000_000;
        final int SQRT_MAX = (int) Math.sqrt(MAX);
        final String wrongMsg = "Goldbach's conjecture is wrong.";

        List<Integer> primes = new ArrayList<>();
        boolean isPrime[] = new boolean[MAX + 1];
        
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= MAX; i++) {
            if (isPrime[i]) {
                primes.add(i);
                if (i <= SQRT_MAX) {
                    for (int j = i * i; j <= MAX; j+= i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int in = Integer.parseInt(br.readLine());

        while (in != 0) {
            goldbach(in, sb, primes, isPrime, wrongMsg);
            in = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
        
    }

    static void goldbach(int n, StringBuilder sb, List<Integer> primes, boolean[] isPrime, String wrongMsg) {
        
        int lowerbound = Collections.binarySearch(primes, n);
        lowerbound = (lowerbound >= 0) ? lowerbound : -lowerbound - 1;
        int idx = lowerbound - 1;
        int top = primes.get(idx);
        while (!isPrime[n - top]) {
            if (idx == 0)
                break;
            idx--;
            top = primes.get(idx);
        }

        if (idx == 0) {
            sb.append(wrongMsg).append('\n');
        } else {
            sb.append(n).append(' ').append('=').append(' ').append(n - top).append(' ').append('+').append(' ').append(top).append('\n');
        }
    }
    
}