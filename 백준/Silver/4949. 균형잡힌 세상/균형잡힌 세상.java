import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        final String yes = "yes";
        final String no = "no";
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        
        while (!line.equals(".")) {
            Deque<Character> stack = new ArrayDeque<>();
            int sl = 0;
            int sr = 0;
            int gl = 0;
            int gr = 0;
            boolean valid = true;
            for (char ch : line.toCharArray()) {
                if (ch == '(') {
                    sl++;
                    stack.push(ch);
                } else if (ch == '[') {
                    gl++;
                    stack.push(ch);
                } else if (ch == ')') {
                    sr++;
                    if (sr > sl || stack.isEmpty() || stack.peek() != '(') {
                        valid = false;
                        break;
                    } else {
                        stack.pop();
                        }
                } else if (ch == ']') {
                    gr++;
                    if (gr > gl || stack.isEmpty() || stack.peek() != '[') {
                        valid = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
            
            if (valid == true && stack.isEmpty()) {
                sb.append(yes).append('\n');
            } else {
                sb.append(no).append('\n');
            }
            
            line = br.readLine();
            
        }
        
        System.out.println(sb);
    }
}