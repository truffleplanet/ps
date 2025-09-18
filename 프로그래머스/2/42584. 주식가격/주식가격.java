import java.util.*;

/*


*/


class Solution {
    public int[] solution(int[] prices) {
        int N = prices.length;
        int[] out = new int[N];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i =0; i < N; i++) {
            
            int val = prices[i];
            if (stack.peek() == -1 || prices[stack.peek()] <= val) {
                stack.push(i);
                continue;
            }
            
            while(stack.size() > 1 && prices[stack.peek()] > val) {
                int x = stack.pop();
                out[x] = i - x;
            }
            
            stack.push(i);
        }
        
        while(stack.size() > 1) {
            int x = stack.pop();
            out[x] = N - x - 1;
        }
        
        return out;
        
    }
}