import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static int[] interval = {180, 180, 1080};   
    static StringBuilder sb;
    static int DAY_MOD = 1440;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int now = 15 * 60;
        int i = 0; 
        int targetDay = DAY_MOD * N;
        while (now < targetDay) {
            now += interval[i++];
            if (i % 3 == 0) {
                i %= 3;
                now += K;
            }
        }
        int dayEnd = targetDay + DAY_MOD;

        int cnt = 0;
        int[] log = new int[5];
        while (now < dayEnd) {
            log[cnt++] = now;
            now += interval[i++];
            if (i % 3 == 0) {
                i %= 3;
                now += K;
            }
        }
        sb.append(cnt).append('\n');

        for (int t = 0; t < cnt; t++) {
            writeTime(log[t]);
        }
        System.out.println(sb);
        
    }
    
    static void writeTime(int min) {
        min %= DAY_MOD;
        int h = min / 60;
        int m = min % 60;
        
        if (h < 10) {
            sb.append('0');
        }
        sb.append(h).append(':');

        if (m < 10) {
            sb.append('0');
        }
        sb.append(m).append('\n');
    }   
}