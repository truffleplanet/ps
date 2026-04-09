/*
brute force O(N^2)
매 위치별로 다음 위치 찾기 -> 10^10
---

찾아야 할 것 -> 나보다 따뜻한 날
temperatures의 idx는 시점이므로, 
삽입된 시점을 순차적으로 stack에 넣는다.
    - 매 삽입 시 자신보다 큰 값이 나올 때까지 pop 하며 삽입하려는 idx - idx_pop이 시간이 된다.
*/

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] out = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int past = stack.pop();
                out[past] = i - past;
            }
                stack.push(i);
        }
        
        return out;
    }
}