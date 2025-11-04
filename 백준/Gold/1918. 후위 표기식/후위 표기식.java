/*
1. 피연산자의 순서는 변하지 않는다.


방법
스택, 문자열

문자열을 앞부터 읽으며 
1. 피연산자는 만나는 즉시 문자열에 추가
2. '('를 만나면 스택에 push
    ')'를 만나면 '('가 나올때까지 pop하며 문자열에 추가 + '('버리기
3, 스택이 비어있거나, 스택의 맨 위가 '('이면 push
4. 스택의 top 연산자가 현재 연산자보다 우선순위가 낮으면 그냥 push
5. 스택 top의 연산자가 우선순위가 같으면 top 연산자의 우선순위가 낮아질 때까지 pop

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] priority = new int[10];
        priority['(' - '('] = 0;
        priority[')' - '('] = 0;
        priority['*' - '('] = 2;
        priority['/' - '('] = 2;
        priority['+' - '('] = 1;
        priority['-' - '('] = 1;
        
        String expr = br.readLine();


        StringBuilder out = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : expr.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                out.append(ch);
                continue;
            }

            if (stack.isEmpty() || ch == '(') {
                stack.push(ch);
                continue;
            }

            if (ch == ')') {
                while (stack.peek() != '(') {
                    out.append(stack.pop());
                }
                stack.pop();
                continue;
            }

            if (priority[ch - '('] > priority[stack.peek() - '(']) {
                stack.push(ch);
                continue;
            }

            while(!stack.isEmpty() && stack.peek() != '(' && priority[ch - '('] <= priority[stack.peek() - '(']) {
                out.append(stack.pop());
            }
            stack.push(ch);
        }

        while(!stack.isEmpty()) {
            out.append(stack.pop());
        }

        System.out.println(out);
    }
}