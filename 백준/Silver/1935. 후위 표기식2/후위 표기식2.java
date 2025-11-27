import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] expr = br.readLine().toCharArray();

        double[] vals = new double[N];
        for (int i = 0; i < N; i++) {
            vals[i] = Integer.parseInt(br.readLine());
        }

        Deque<Double> stack = new ArrayDeque<>();

        for (char c : expr) {
            if (Character.isLetter(c)) {
                stack.push(vals[c - 'A']);
            } else {
                double b = stack.pop();
                double a = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }

        System.out.printf("%.2f", stack.pop());
        
    }
}