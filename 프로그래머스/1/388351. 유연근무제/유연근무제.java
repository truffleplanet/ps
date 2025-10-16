/*
시작일 1
주말, (6, 7)
2, 주말 인덱스는 (5, 6)
3 -> (4, 5)
4 -> (3, 4)
5 -> (2, 3)
6 -> (1, 2)
7 -> (1, 7)

규칙 있긴한데 그냥 맵핑하자
*/


class Solution {
    
    final int[][] weekends = {{5, 6},
                              {4, 5},
                              {3, 4},
                              {2, 3},
                              {1, 2},
                              {0, 1},
                              {6, 0}};
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        boolean[] isWeekend = new boolean[7];
        isWeekend[weekends[startday - 1][0]] = isWeekend[weekends[startday - 1][1]] = true;
        
        int N = schedules.length;
        int cnt = 0;
        
        for (int i = 0; i < N; i++) {
            
            int targetT = schedules[i];
            int targetH = targetT / 100;
            int targetM = targetT % 100;
            int time = targetH * 60 + targetM;
            int cap = time + 10;
            
            int[] stamp = timelogs[i];
            boolean isWinner = true;
            
            for (int j = 0; j < 7; j++) {
                
                if (isWeekend[j])
                    continue;
                
                int hour = stamp[j] / 100;
                int min = stamp[j] % 100;
                int t = hour * 60 + min;
                
                if (t > cap) {
                    isWinner = false;
                    break;
                }
            }
            
            if (isWinner)
                cnt++;
        }
        
        return cnt;
    }
}