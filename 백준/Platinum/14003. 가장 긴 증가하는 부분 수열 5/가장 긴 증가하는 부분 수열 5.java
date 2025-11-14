/*
시간복잡도가 초과하지 않도록
이진탐색 방식으로 LIS의 길이를 구한다.

다만 부분 수열을 복원해야 하므로, 기존 arr를 기준으로
조상 인덱스를 저장해두고 역추적한다.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dpIdx = new int[N];
        int[] ancestor = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> dp = new ArrayList<>();
        dp.add(arr[0]);
        dpIdx[0] = 0;
        ancestor[0] = -1;

        for (int i = 1; i < N; i++) {
            if (arr[i] > dp.get(dp.size() - 1)) {
                dp.add(arr[i]);
                int newLength = dp.size() - 1;
                dpIdx[newLength] = i;
                ancestor[i] = dpIdx[newLength - 1];
            } else {
                int insertionPoint = binarySearch(dp, arr[i]);
                dp.set(insertionPoint, arr[i]);
                dpIdx[insertionPoint] = i;
                
                if (insertionPoint > 0) {
                    ancestor[i] = dpIdx[insertionPoint - 1];
                } else {
                    ancestor[i] = -1;
                }
                
            }
        }

        int[] lis = new int[dp.size()];
        int currentIdx = dpIdx[dp.size() - 1];

        for (int i = dp.size() - 1; i >= 0; i--) {
            lis[i] = arr[currentIdx];
            currentIdx = ancestor[currentIdx];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lis.length).append('\n');
        for (int val : lis) {
            sb.append(val).append(' ');
        }

        System.out.println(sb);
        
    }

    static int binarySearch(List<Integer> lst, int key) {
        int l = 0;
        int r = lst.size();

        while (l < r) {
            int mid = (l + r) >> 1;

            if (key <= lst.get(mid)) {
                r = mid;
            } else {
                l = mid + 1; 
            }
            
        }

        return l;
    }
}