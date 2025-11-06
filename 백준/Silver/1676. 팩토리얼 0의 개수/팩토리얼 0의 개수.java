/*
brute-force
1. 500 팩토리얼 수행
2. 문자열로 변경 후 순회하며 작업 수행.


*/

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        BigInteger fact = new BigInteger("1");

        for (int i = 1; i <= N; i++) {
            fact = fact.multiply(new BigInteger(String.valueOf(i)));
        }

        char[] s = fact.toString().toCharArray();
        int zeroCount = 0;
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] != '0') {
                break;
            }
            zeroCount++;
        }

        System.out.println(zeroCount);
        
    }
}