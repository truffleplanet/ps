import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int midIdx = N / 2;
        int offSet = 4000;
        int[] count = new int[8001];
        int total = 0;
        int min = 4001;
        int max = -4001;
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(br.readLine());
            total += v;
            min = Math.min(min, v);
            max = Math.max(max, v);
            v += offSet;
            count[v]++;
        }

        int mean = (int) Math.round(((double) total / N));
        int range = max - min;

        int maxR = max + offSet;
        int minR = min + offSet;

        int modeCnt = 0;

        int idx = -1;
        int mid = -1;
        for (int i = minR; i <= maxR; i++) {
            modeCnt = Math.max(modeCnt, count[i]);
            int nIdx = idx + count[i]; // (idx, nIdx]
            if (midIdx > idx && midIdx <= nIdx) {
                mid = i - offSet;
            }
            idx = nIdx;
        }

        int mode = -1;
        int modeRank = 0;
        for (int i = minR; i <= maxR; i++) {
            if (count[i] == modeCnt) {
                if (modeRank < 2) {
                    modeRank++;
                    mode = i - offSet;
                } else {
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(mean).append('\n').
            append(mid).append('\n').
            append(mode).append('\n').
            append(range);

        System.out.println(sb);
        
    }
}