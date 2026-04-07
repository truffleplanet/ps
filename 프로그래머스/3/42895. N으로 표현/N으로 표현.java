import java.util.*;

class Solution {
    public int solution(int N, int number) {
        HashSet<Integer>[] dp = new HashSet[9];
        for (int i = 1; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }
        dp[1].add(N);
        dp[2].add(N * N);
        dp[2].add(N + N);
        dp[2].add(N * 11);
        dp[2].add(N / N);

        for (int i = 1; i <= 8; i++) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < i; k++) {
                sb.append(N);
            }
            dp[i].add(Integer.parseInt(sb.toString()));
            
            for (int j = 1; j < i; j++) {
                for (int x : dp[j]) {
                    for (int y : dp[i - j]) {
                        dp[i].add(x + y);
                        dp[i].add(x - y);
                        dp[i].add(x * y);
                        if (y != 0) {
                            dp[i].add(x / y);
                        }
                    }
                }
            }
            if (dp[i].contains(number)) {
                return i;
            }
        }        
        return -1;
    }
}