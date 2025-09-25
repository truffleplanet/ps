// 최대공약수 -- 유클리드 호제법
// 최소공배수 -- x * y // gcd

import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int gcd(int x, int y) {
        if (x % y == 0) 
            return y;
        else 
            return gcd(y, x % y); // y가 x보다 더 크면 자연스럽게 swap됨
        
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int gcdVal = gcd(x, y);
        int lcmVal = (x * y) / gcdVal; // 이는 나누어 떨어짐이 보장됨. 공약수로 나누니깐

        sb.append(gcdVal).append('\n').append(lcmVal);
        System.out.println(sb);
        
    }
}