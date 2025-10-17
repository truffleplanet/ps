/*
1. 모든 비밀코드 조합 만들기 (30C5)

2. 대조하기
*/

import java.util.*;

class Solution {
    
    int[][] q;
    int[] ans;
    int[] code;
    int N;
    int R;
    int M;
    int count;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.N = n;
        this.q = q;
        this.ans = ans;
        this.R = 5;
        this.M = q.length;
        this.code = new int[5];
        this.count = 0;
        
        comb(1, 0);
        return count;
    }
    
    public void comb(int start, int cnt) {
        if (cnt == R) {
            for (int i = 0; i < M; i++) {
                if (!match(q[i], code, ans[i]))
                    return;
            }
            count++;
            return;
        }
        
        for (int i = start; i <= N; i++) {
            code[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }
    
    public boolean match(int[] query, int[] code, int ans) {
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (query[i] == code[j])
                    cnt++;
            }
        }
        return cnt == ans;
    }
    
}