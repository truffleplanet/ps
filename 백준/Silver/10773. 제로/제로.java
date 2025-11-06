import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        Deque<Long> stack = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            long v = Long.parseLong(br.readLine());
            if (v == 0) {
                stack.pop();
            } else {
                stack.push(v); 
            }
        }

        long ans = 0;
        while(!stack.isEmpty()) {
            ans += stack.pop();
        }

        System.out.println(ans);
    }
}