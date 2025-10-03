/*

연속된 소수의 합으로 자연수를 나타내고 싶으면 적어도..

1.  N 이하의 소수 리스트가 필요함 
2.  합으로 나타낼 수 있는 경우의 수는 백트래킹으로 구해보자.(단순 부분집합으로 하면 시간이 걱정된다)


구현 1:
소수 리스트 찾기는 에라토스테네스의 채 활용

구현 2: 
연속된이라는 조건..
문제를 잘 읽자.. (나는 잘 안 읽었다..)
투포인터 
i, j 
합이 N보다 작거나 같으면 j 밀고 
합이 N보다 크면 i 밀고



*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static List<Integer> primes;
    static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        primes = new ArrayList<>();

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        int sqrtN = (int) Math.sqrt(N);
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
                if (i <= sqrtN) {
                    for (int j = i * i; j <= N; j += i) { // i*i오버플로우 걱정 x 이미 제곱근 된
                        isPrime[j] = false;
                    }
                }
            }
        }// 구현 1 끝.
        // System.out.println(primes);

        int k = primes.size();
        int ans = 0;
        int sum = (k != 0) ? primes.get(0) : 0;
        for (int i = 0, j = 1; j <= k;) {
            if (sum == N) {
                ans++;
            }

            if (sum > N) {
                sum -= primes.get(i);
                i++;
            } else {
                if (j == k)
                    break;
                
                sum += primes.get(j);
                j++;
            }
            
        }

        System.out.println(ans);
        
    }
    
}