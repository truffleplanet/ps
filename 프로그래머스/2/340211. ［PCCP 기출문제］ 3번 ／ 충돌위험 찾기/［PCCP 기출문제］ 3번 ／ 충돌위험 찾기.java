import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Map<Long, Integer> stamp = new HashMap<>();
        
        for (int[] route : routes) {
            int r = points[route[0] - 1][0];
            int c = points[route[0] - 1][1];
            int t = 0;
            long key = getKey(r, c, t);
            stamp.put(key, stamp.getOrDefault(key, 0) + 1);

            for (int i = 1; i < route.length; i++) {
                int nr = points[route[i] - 1][0];
                int nc = points[route[i] - 1][1];

                int d = (r > nr) ? -1 : 1;
                while(r != nr) {
                    r += d;
                    t++;
                    key = getKey(r, c, t);
                    stamp.put(key, stamp.getOrDefault(key, 0) + 1);
                }
                
                d = (c > nc) ? -1 : 1;
                while (c != nc) {
                    c += d;
                    t++;
                    key = getKey(r, c, t);
                    stamp.put(key, stamp.getOrDefault(key, 0) + 1);
                }
            }
        }
        
        int answer = 0;
        
        for (int val : stamp.values()) {
            if (val >= 2) {
                answer++;
            }
        }
        
        return answer;
    }
    
    long getKey(long r, long c, long t) {
        return (r << 40) + (c << 32) + t;
    }
    
}