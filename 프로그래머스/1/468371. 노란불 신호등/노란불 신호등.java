/*
신호등의 상태는 순환한다.
각 신호등마다 신호의 지속 시간이 다르다.
모든 신호등이 노란불이면 정전이 발생한다.

모든 신호등이 노란불이 되는 가장 빠른 시각을 return하라. 
그런 경우가 존재하지 않는다면 -1을 return해야한다.

-- 19 * 17 * 13 * 11 * 9 = 415,701
각자의 주기가 소수여도, 총 봐야할 양은 40만정도임.
최소공배수를 구하고, 그 지점까지 완전탐색한다. 

최소 공배수는? 
전부 곱하고 gcd로 나누는 것. 
20^5 은 int범위이므로, 각 길이를 전부 곱하고, gcd로 (n - 1)번 나누면 된다.
*/

class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        int lcm = 1;
        for (int[] s : signals) {
            int len = s[0] + s[1] + s[2];
            lcm = lcm / gcd(lcm, len) * len;
        }
        
        for (int t = 1; t <= lcm; t++) {
            boolean allYellow = true;
            for (int[] s : signals) {
                int len = s[0] + s[1] + s[2];
                int v = (t - 1) % len;
                if (!(v >= s[0] && v < s[0] + s[1])) {
                    allYellow = false;
                    break;
                }
            }
            if (allYellow) {
                return t;
            }
        } 
        return -1;
    }
    
    private int gcd(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }
}