/*
사전준비.
1. 진법을 바꿔서 계산할 수 있는 함수 calculate를 만든다.
2. 출력을 위해, 출력양식에 맞게 표현식을 변환해줄 함수를 만든다.

풀이:
1. 결과를 모르는 표현식과 결과를 아는 표현식을 분류한다.
2. 결과를 아는 표현식을 통해 불가능한 진법을 제거한다. 즉 가능한 진법의 경우들만 남겨둔다.
3. 결과를 모르는 표현식을 통해 불가능한 진법을 제거한다. (i진법이면 i-1까지의 숫자만 존재해야 한다.)
4. 결과를 모르는 표현식 중 진법 후보를 통해 결과를 확정할 수 있으면 결과를, 아니면 ?를 적는다.
*/

import java.util.*;

class Solution {
    
    static class Expr {
        String a;
        String b;
        String op;
        String c = null;
        
        public Expr(String a, String b, String op) {
            this.a = a;
            this.b = b;
            this.op = op;
        }
        
        public String toString() {
            return String.format("%s %s %s = %s", a, op, b, c);
        }
        
    }
    
    public String[] solution(String[] expressions) {
        
        List<Expr> X_List = new ArrayList<>();
        
        boolean[] possibleBase = new boolean[10];
        Arrays.fill(possibleBase, true);
        possibleBase[0] = possibleBase[1] = false;
        
        // 입력 
        for (String expression : expressions) {
            String[] tokens = expression.split(" ");
            String a = tokens[0];
            String op = tokens[1];
            String b = tokens[2];
            String c = tokens[4];
            
            // 가능한 기수표현만 남기기
            removeImpossible(possibleBase, a);
            removeImpossible(possibleBase, b);
            
            if (c.equals("X")) {
                X_List.add(new Expr(a, b, op));
                continue;
            } 
            
            removeImpossible(possibleBase, c);
            
            for (int base = 2; base <= 9; base++) {
                if (!possibleBase[base]) {
                    continue;
                }
                
                if (!calculate(a, b, op, base).equals(c)) {
                    possibleBase[base] = false;
                }
            }
        }
            
        // 계산식에 정답 할당            
        for (Expr expr : X_List) {
            for (int base = 2; base <= 9; base++) {
                if (!possibleBase[base]) {
                    continue;
                }
                if (expr.c == null) {
                    expr.c = calculate(expr, base);
                    continue;
                } 
                
                if (!expr.c.equals(calculate(expr, base))) {
                    expr.c = "?";
                    break;
                }
            }

            if (expr.c == null) {
                expr.c = "?";
            }
        }

        // 출력 
        String[] out = new String[X_List.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = X_List.get(i).toString();
        }
        
        return out;
    }
    
    public void removeImpossible(boolean[] possibleBase, String s) {
        int bound = 0;
        for (char ch : s.toCharArray()) {
            bound = Math.max(bound, ch - '0');
        }
        for (int i = 2; i <= bound; i++) {
            possibleBase[i] = false;
        }
    }
    
    public String calculate(String strA, String strB, String op, int base) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < strA.length(); i++) {
            a += (strA.charAt(i) - '0') * ((int) Math.pow(base, strA.length() - i - 1));
        }
        for (int i = 0; i < strB.length(); i++) {
            b += (strB.charAt(i) - '0') * ((int) Math.pow(base, strB.length() - i - 1));
        }
        
        int c;
        if (op.equals("+")) {
            c = a + b;
        } else {
            c = a - b;
        }
        
        if (c == 0) {
            return "0";
        }
        
        StringBuilder strC = new StringBuilder();
        
        while (c != 0) {
            int temp = c % base;
            strC.append(temp);
            c = c / base;
        }
        return strC.reverse().toString();
    }
    
    public String calculate(Expr expr, int base) {
        return calculate(expr.a, expr.b, expr.op, base);
    }
    
}