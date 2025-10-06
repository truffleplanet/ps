/*
사전 사항:
4,000,000,000,000
최대 끈 길이가 4조 범위.

long 범위 
9,223,372,036,854,775,807
int 범위 
2,147,483,627

문제 정의:
어떤 수가 주어졌을 때, 그 수가 두 소수의 합으로 표현될 수 있는지 판단하라

풀이:
먼저 두 끈의 기존 상태에는 관심없고, 합친 상태에서 두 소수의 합으로 표현할 수 있는지만 관심 있음.
따라서 기존 상태는 상관없이 일단 합친다.

편의를 위해 N이 소수인지 표현하는 명제를 prime(N)
두 소수의 합으로 나타낼 수 있는지 표현하는 명제를 primeSum(N)이라고 하자.

primeSum(N)인지 에라토스테네스의 채 방식으로 기존 문제들 풀듯이 확인하려면..
N밑의 모든 소수를 구하여 소수 리스트를 만들고, 또한 prime(N)을 N범위까지 미리 구해두면된다.
그런데 문제는 범위가 4조이다. prime(N)을 구하는데에만 4조번의 연산이 요구되므로 시간제한을 초과한다.

--
2 이상의 모든 소수는 홀수이다.

만약에 N이 짝수이면 N은 홀+홀로 표현할 수 있고
골드바흐 추측은 4이상의 모든 짝수는 두 소수의 합으로 표현할 수 있다는 명제이고
400경인가까지는 증명되었다고 함. 따라서 N이 짝수이고 4이상이면 isPrime(N) == true임

N이 홀수이면 N은 홀 + 짝으로 표현할 수 있다. 그런데 소수인 짝수는 2뿐이니
N이 홀수이면 2+소수 꼴이여야 한다.
그러면 이게 소수인지는 판단을 해야하는데.. 이건 미리 테이블을 만들어놓기는 힘드니깐..(4조)
소수 한개를 판정하는데에는 root(N) 시간이 걸리고 (에라토스 테네스의 체)
루트 4조는 2백만임. 즉 한번 prime(약 4조)를 할 때에 200만번 나눠보면 될 일임. 그런데 테케가 500개이니깐 이것도 너무 느림.
따라서 200만 안 넘는 소수 리스트는 미리 구해두고,
root(N)이 넘지 않는 소수까지만 나눠보며 prime(N) 판정 하면 된다.

200만까지의 소수는 약 15만개라고 하고, 
그러면 약 N이 4조 되는 케이스만 입력되어도 
150000*500 = 75,000,000으로 1초 통과 가능하다.
다만 같은 케이스는 또 계산하기 싫으니 HashMap만들어두고, 이미 계산된 케이스면 불러와서 쓸 수 있게 해보자.(생각해보니 굳이여서 안함)

아 끈의 길이가 다를 필요는 없다!
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static List<Long> primes;
    final static String YES = "YES";
    final static String NO = "NO";
    
    public static void main(String[] args) throws Exception {
        // init 
        final int SQRT_MAX = 2_000_000;
        boolean[] isPrime = new boolean[SQRT_MAX + 1];
        primes = new ArrayList<>();
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        // 에라토스테네스의 채로 소수 리스트 구하기
        int i_range = (int) Math.sqrt(SQRT_MAX);
        for (int i = 2; i < SQRT_MAX; i++) {
            if (isPrime[i]) {
                primes.add((long) i);
                if (i <= i_range) {
                    for (int j = i * i; j < SQRT_MAX; j += i) {
                        isPrime[j] = false;
                    }
                }
            }
        }
        
        // input 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int $ = 0; $ < T; $++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = 0;
            n += Long.parseLong(st.nextToken());
            n += Long.parseLong(st.nextToken());
            primeSum(n, sb);
        }

        System.out.println(sb);

    }

    private static void primeSum(long n, StringBuilder sb) {
        boolean isEven = (n % 2 == 0);
        
        if (n < 4) {
            sb.append(NO).append('\n');
            return;
        }

        // goldbach's conjecture
        if (isEven) {
            sb.append(YES).append('\n');
            return;
        }

        long b = n - 2; // n == 2 + b 
        long sqrt_b = (long) Math.sqrt(b);
        boolean isPrime = true;

        for (long p : primes) {
            if (p > sqrt_b) {
                break;
            }
            if (b % p == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            sb.append(YES).append('\n');
        } else {
            sb.append(NO).append('\n');
        }
        
    }
}