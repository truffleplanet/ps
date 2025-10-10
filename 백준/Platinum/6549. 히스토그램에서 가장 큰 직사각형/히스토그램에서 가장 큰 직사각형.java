/*
100,000,000,000,000
9,223,372,036,854,775,807
*/


import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String in = br.readLine();
        while(in.charAt(0) != '0') {
            int[] histo = getHisto(in);
            bw.write(Long.toString(getLargestArea(histo)));
            bw.write('\n');
            in = br.readLine();
        }

        br.close();
        bw.flush();
        bw.close();
        
    }

    static int[] getHisto(String in) throws Exception {
        StringTokenizer st = new StringTokenizer(in);
        int N = Integer.parseInt(st.nextToken());
        int[] out = new int[N];
        for (int i = 0; i < N; i++) {
            out[i] = Integer.parseInt(st.nextToken());
        }
        return out;
    }

    static long getLargestArea(int[] histo) {
        int N = histo.length;
        long maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        
        for (int i = 0; i < N; i++) {
            int h = histo[i];
            while(stack.peek() != -1 && histo[stack.peek()] >= h) {
                long cH = histo[stack.pop()];
                long cW = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, cH * cW);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            long cH = histo[stack.pop()];
            long cW = N - stack.peek() - 1;
            maxArea = Math.max(maxArea, cH * cW);
        }

        return maxArea;
    }

}

    