/*
바구니는 스택으로 
stack.peek() == x이면 pop하고 not push cnt += 2 (sentinel -1)
else 
push

뽑기 구현. 

메모리를 더 써서 각 열을 stack으로 구성.
해당 열에서 peek() != -1이면 pop해서 꺼내오기.
*/

import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int N = board.length;
        
        Deque<Integer> bucket = new ArrayDeque<>();
        bucket.push(-1);
        
        Deque<Integer>[] box = new Deque[N + 1];
        
        for (int i = 1; i <= N; i++) {
            box[i] = new ArrayDeque<>();
            box[i].push(-1);
        }
        
        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0 ; i--) {
                int val = board[i][j];
                if (val != 0) {
                    box[j + 1].push(val);
                }
            }
        }
        
        int cnt = 0;
        for (int move : moves) {
            int val = box[move].peek();
            
            if (val == -1)
                continue;
            
            box[move].pop();
            if (bucket.peek() == val) {
                bucket.pop();
                cnt += 2;
            } else {
                bucket.push(val);
            }
            
        }

        return cnt;
    }
}