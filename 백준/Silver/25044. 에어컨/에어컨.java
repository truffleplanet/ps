import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static final int[] interval = {180, 180, 1080};   
    static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final int DAY_MOD = 1440;
    
    static char[] buffer = new char[6];
    static StringBuilder sb;
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder(32);
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int now = 15 * 60;
        int i = 0; 
        int targetDay = DAY_MOD * N;
        while (now < targetDay) {
            now += interval[i];
            ++i;
            if (i == 3) {
                i = 0;
                now += K;
            }
        }
        
        int dayEnd = targetDay + DAY_MOD;
        int cnt = 0;
        int[] log = new int[5];
        while (now < dayEnd) {
            log[cnt] = now;
            ++cnt;
            now += interval[i];
            ++i;
            if (i == 3) {
                i = 0;
                now += K;
            }
        }
        sb.append(cnt).append('\n');

        buffer[2] = ':';
        buffer[5] = '\n';

        for (int t = 0; t < cnt; ++t) {
            writeTime(log[t]);
        }
        System.out.println(sb);
        
    }
    
    static void writeTime(int min) {
        min %= DAY_MOD;
        int h = min / 60;
        int m = min % 60;

        buffer[0] = DIGITS[h / 10];
        buffer[1] = DIGITS[h % 10];
        buffer[3] = DIGITS[m / 10];
        buffer[4] = DIGITS[m % 10]; 
        sb.append(buffer);
    }   
}