/*
prev : 10초전 혹은 처음 위치

next: 10 초 후, 혹은 마지막 위치

오프닝 건너뛰기: 현재 상태가 오프닝 구간인 경우에 오프닝 끝나는 위치로 이동. 

*/


class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int t = toTime(pos);
        int op_s = toTime(op_start);
        int op_e = toTime(op_end);
        int max = toTime(video_len);
        
        for (String cmd : commands) {
            if (t >= op_s && t < op_e) 
                t = op_e;
            if (cmd.equals("next")) {
                t = clip(t + 10, max);
            } else if (cmd.equals("prev")) {
                t = clip(t - 10, max);
            }
        }
        if (t >= op_s && t < op_e) 
            t = op_e;

        String answer = toStringTime(t);
        return answer;
    }
    
    int toTime(String time) {
        int min = 10 * (time.charAt(0) - '0') + time.charAt(1) - '0';
        int sec = 10 * (time.charAt(3) - '0') + time.charAt(4) - '0';
        return 60 * min + sec;
    }
    
    String toStringTime(int time) {
        int min = time / 60;
        int sec = time % 60;
        return "" + (min / 10) + (min % 10) + ":" + (sec / 10) + (sec % 10);
    }
    
    int clip(int time, int max) {
        if (time < 0) {
            return 0;
        }
        if (time > max) {
            time = max;
        }
        return time;
    }
    
}