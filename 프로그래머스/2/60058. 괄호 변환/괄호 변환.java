import java.util.*;

class Solution {
    public String solution(String p) {
        return solver(p);
    }
    
    public String solver(String s) {
        // 1.
        int N = s.length();
        if (N == 0) {
            return "";
        }
        
        // 2. ))(( 같은 경우는 분리 불가함/ 즉 ..
        int l = 0;
        int r = 0;
        int split = 0;
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }
            
            if (l == r) {
                split = i;
                break;
            }
        }
        
        String u = s.substring(0, split + 1);
        String v = s.substring(split + 1, N);
        
        //3
        if (isComplete(u)) {
            return u + solver(v);
        } else {  // 4
            String e = "(";
            e += solver(v);
            e += ")";
            u = u.substring(1, u.length() - 1);
            for (char par : u.toCharArray()) {
                if (par == '(') {
                    par = ')';
                } else {
                    par = '(';
                }
                e += par;
            }
            return e;
        }
        
    }
    
    public boolean isComplete(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        int l = 0;
        int r = 0;
        for (char ch : s.toCharArray()) {
            
            if (ch == '(') {
                l++;
            } else {
                r++;
            }
            
            if (r > l) {
                return false;
            }
            
            if (stack.isEmpty()) {
                stack.push(ch);
                continue;
            }
            
            if (ch == ')') {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        
        return true;
        
    }
}