/*
3의 배수와 5의배수가 3연속으로 등장하는 것은 불가능하다. 
따라서 숫자가 한번은 입력으로 들어온다.

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = -1;
        for (int i = 0; i < 3; i++) {
            String x = br.readLine();
            char ch = x.charAt(0);
            if (ch >= '0' && ch <= '9') {
                n = Integer.parseInt(x) + 3 - i;
                break;
            }
        }

        System.out.println(fizzbuzz(n));
    }

    public static String fizzbuzz(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(n);
        }
    }


}