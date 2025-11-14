import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        long best = Long.MAX_VALUE;
        long[] ans = new long[3];
        boolean isOn = true;

        for (int i = 0; i < N - 2; i++) {
            
            if (!isOn)
                break;
            int left = i + 1;
            int right = N - 1;
            
            while (left < right) {
                long sum = arr[i] + arr[left] + arr[right];
                long absSum = Math.abs(sum);
                if (absSum < best) {
                    best = absSum;
                    ans[0] = arr[i];
                    ans[1] = arr[left];
                    ans[2] = arr[right];
                }
                
                if (sum == 0) {
                    isOn = false;
                    break;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (long val : ans) {
            sb.append(val).append(' ');
        }

        System.out.println(sb);
    }
}