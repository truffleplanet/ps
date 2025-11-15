/*
연산자의 우선순위를 자유롭게 재정의해서
표현식을 가장 큰 숫자로 만들기

- expression의 길이는 최대 100이고, 괄호 없이 3가지 연산자 뿐임.

- 우선순위 순열로 완탐한다면?
100 * 3(우선순위인 연산자부터 계산하기) * 6! 
그냥 완탐하자~
*/

import java.lang.*;
import java.util.*;

class Solution {
    
    final int ADD = 0;
    final int SUB = 1;
    final int MUL = 2;
    final char[] oper2Char = {'+', '-', '*'};

    int[] order;
    boolean[] selected;
    long ans;
    ArrayDeque<String> exprQ;
    
    public long solution(String expression) {
        long answer = 0;
        
        order = new int[3];
        selected = new boolean[3];
        ans = 0;
        
        exprQ = new ArrayDeque<>();
        int l = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9')
                continue;
            else {
                exprQ.offer(expression.substring(l, i));
                exprQ.offer(expression.substring(i, i +1));
                l = i + 1;
            }
        }
        exprQ.offer(expression.substring(l, expression.length()));
        
        permu(0);
        return ans;
    }
    
    public void permu(int cnt) {
        if (cnt == 3) {
            ArrayDeque<String> q = (ArrayDeque<String>) exprQ.clone();
            for (int operator : order) {
                q = calculate(q, operator);
            }
            ans = Math.max(ans, Math.abs(Long.parseLong(q.poll())));
            return;
        }
        
        for (int i = 0; i < 3; i++) {
            if (!selected[i]) {
                selected[i] = true;
                order[cnt] = i;
                permu(cnt + 1);
                selected[i] = false;
            }
        }
        
    }
    
    public ArrayDeque<String> calculate(ArrayDeque<String> q, int operator) {
        // 문자열을 읽으면서, 목표 연산자가 나오면 좌우 계산.
        // 아니면, 왼쪽을 그대로 sb에 기록. 이게 어렵네..
        if (q.size() == 1) {
            return q;
        }
        ArrayDeque<String> out = new ArrayDeque<>();
        char targetOper = oper2Char[operator];
        
        String prev = q.poll();
        while (!q.isEmpty()) {
            char oper = q.poll().charAt(0);
            String next = q.poll();
            
            if (oper == targetOper) {
                long newVal = 0;
                if (operator == ADD) {
                    newVal = Long.parseLong(prev) + Long.parseLong(next);
                } else if (operator == SUB) {
                    newVal = Long.parseLong(prev) - Long.parseLong(next);
                } else {
                    newVal = Long.parseLong(prev) * Long.parseLong(next);
                }
                prev = String.valueOf(newVal);
            } else {
                out.offer(prev);
                out.offer(String.valueOf(oper));
                prev = next;
            }
        }
        out.offer(prev);
        
        return out;
    }
    
}