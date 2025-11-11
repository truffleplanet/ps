/*
1. 전부 양수이면 --> 가장 작은 것 두개 합치기
2. 전부 음수이면 --> 가장 큰 것 두개 합치기 

3. 섞이면..  --> [-99, 1, 2]  -> 1 + 2


--> 
음수와 양수 분리
둘중 하나 비었으면 위의 방식으로 하고 return

음수 리스트에서 
양수리스트에, 절대값 가까운 것 이진탐색 
모두 반복하며 최솟값 갱신 O(NlogN)

마지막에는 음수, 양수내 best와, 이진탐색으로 구한 best 비교 후 끝.

*/
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (v < 0) {
                negative.add(v);
            } else {
                positive.add(v);
            }
        }

        int[] ans = new int[2];
        int best = Integer.MAX_VALUE;
        if (negative.size() >= 2) {
            best = Math.abs(negative.get(negative.size() - 1) + negative.get(negative.size() - 2));
            ans[0] = negative.get(negative.size() - 2);
            ans[1] = negative.get(negative.size() - 1);
        }

        if (positive.size() >= 2) {
            int b = positive.get(0) + positive.get(1);
            if (best > b) {
                best = b;
                ans[0] = positive.get(0);
                ans[1] = positive.get(1);
            }
        }

        if (!positive.isEmpty() && !negative.isEmpty()) {
            for (int i = 0; i < negative.size(); i++) {
                int negV = negative.get(i);
                int pKey = binarySearch(positive, -negV);
                int posV = positive.get(pKey);
                int b = Math.abs(posV + negV);
                if (b < best) {
                    best = b;
                    ans[0] = negV;
                    ans[1] = posV;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans[0]).append(' ').append(ans[1]);
        System.out.println(sb);
    }

    // 양수리스트에서 key와 가장 가까운 값 찾기. (key는 음수값을 양수로 변환해서 넣어줌)

    public static int binarySearch(List<Integer> lst, int key) {
        int l = 0;
        int r = lst.size() - 1;

        // key와 가장 가까운 값 찾기
        while (l < r) {
            int mid = (l + r) / 2;
            int val = lst.get(mid);

            if (val < key) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        int ans = l;
        if (l > 0) {
            int v1 = Math.abs(key - lst.get(l));
            int v2 = Math.abs(key - lst.get(l - 1));
            if (v2 < v1) {
                ans = l - 1;
            }
        }
        return ans;
    }
}