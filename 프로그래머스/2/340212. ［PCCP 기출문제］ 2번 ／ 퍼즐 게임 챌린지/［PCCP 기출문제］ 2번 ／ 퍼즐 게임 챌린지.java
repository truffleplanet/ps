/*
diff > level이면
(diff - level) * (time_prev + time_cur) + time_cur

시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값.

level은 diff의 최대값보다 클 필요는 없고
어떤 diff가 초과하면 그것보다 큰 경우는 볼 필요없음
level을 파라미터로 이진탐색 가능하네. 
ㄱㄱ.
*/

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {    
        
        int max = 0; 
        for (int diff : diffs) {
            max = Math.max(max, diff);
        }
        
        int l = 1;
        int r = max;
        while (l <= r) {
            int mid =  (l + r) / 2;
            
            if (pass(mid, diffs, times, limit)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    }
    
    boolean pass(int level, int[] diffs, int[] times, long limit) {
        int N = diffs.length;
        long time = 0;
        for (int i = 0; i < N; i++) {
            if (diffs[i] <= level) {
                time += times[i];
            } else {
                time += ((times[i-1] + times[i]) * (diffs[i] - level)) + times[i];
            }
            
            if (time > limit) {
                return false;
            }
        }
        return true;
    }
}