class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][N];
        
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }
        
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[i][j] == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
                }   
            }
        }
        
        int answer = 0;        
        for (int j = 0; j < N; j++) {
            answer = Math.max(answer, dp[N - 1][j]);
        }
        
        return answer;
    }
}