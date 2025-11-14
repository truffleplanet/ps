/*
이전에 두 개를 선택하는 문제는..
음수 양수 리스트를 나누고
각자 정렬 후 
- 음수 내 합 최소
- 양수 내 합 최소
- 음수 하나 셀렉트 후, 가장 유사한 양수 이진탐색으로 구하고 그 중 최소

세 경우 중 최소로 정답을 구하였음.
------------

3개의 선택에서는 어떻게 속도를 낼 수 있을까..?
[-97, -6, -2, 6, 98]

선택의 경우의 수
- 양수 3개
- 음수 3개
- 음수 2개 양수 1개
- 양수 2개 음수 1개

음.. 이건 5000c2 + 5000c2 가 들어가서.. 아슬아슬하게 통과할듯?

구현 -> 모든 iteration에서 최솟값 갱신하며, 갱신 시 3개의 값 기록하기.

더 좋은 방법없나..? 


구현 설계;
먼저 양수가 3개 이상이면, 최소 양수 3개 합 하고 index 갱신
.. 그런식으로 계속 

필요한 함수 
-> 조합
-> 최솟값 갱신 후 정답 배열에 값 넣어주는 함수
-> 이진탐색 (lower bound후 바로 아래값도 한번 확인하기)

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static long ans;
    static long[] ansArr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        List<Long> positives = new ArrayList();
        List<Long> negatives = new ArrayList();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long val = Long.parseLong(st.nextToken());
            if (val > 0) {
                positives.add(val);
            } else {
                negatives.add(val);
            }
        }

        Collections.sort(positives);
        Collections.sort(negatives);

        ans = Long.MAX_VALUE;
        ansArr = new long[3];
        
        if (positives.size() >= 3) {
            update(positives.get(0), positives.get(1), positives.get(2));
        }

        if (negatives.size() >= 3) {
            int negSize = negatives.size();
            update(negatives.get(negSize - 1), negatives.get(negSize - 2), negatives.get(negSize - 3));
        }

        if (positives.size() >= 1 && negatives.size() >= 2) {
            comb(negatives, positives, 0, 0, new boolean[negatives.size()], new long[2]);
        }

        if (negatives.size() >= 1 && positives.size() >= 2) {
            comb(positives, negatives, 0, 0, new boolean[positives.size()], new long[2]);
        }

        StringBuilder sb = new StringBuilder();
        for (long val : ansArr) {
            sb.append(val).append(' ');
        }
        
        System.out.println(sb);
        
    }

    public static void comb(List<Long> list, List<Long> other, int start, int cnt, boolean[] selected, long[] selectedVal) {
        if (cnt == 2) {
            long key = selectedVal[0] + selectedVal[1];
            int otherIdx = lowerBound(other, -key);
            long third = other.get(otherIdx);
            update(selectedVal[0], selectedVal[1], third);
            return;
        }

        for (int i = start; i < list.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                selectedVal[cnt] = list.get(i);
                comb(list, other, i + 1, cnt + 1, selected, selectedVal);
                selected[i] = false;
            }
        }
        
    }

    public static void update(long a, long b, long c) {
        long sum = Math.abs(a + b + c);
        if (sum < ans) {
            ans = sum;
            long[] arr = {a, b, c};
            Arrays.sort(arr);
            for (int i = 0; i < 3; i++) {
                ansArr[i] = arr[i];
            }
        }
    }

    public static int lowerBound(List<Long> list, long key) {
        int l = 0;
        int r = list.size();

        if (key > list.get(r - 1)) {
            return r - 1;
        }

        while (l < r) {
            int mid = (l + r) >> 1;

            if (key <= list.get(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        long diff = Math.abs(key - list.get(l));
        
        if (l > 0) {
            if (diff > Math.abs(key - list.get(l - 1)))
                l = l - 1;
        }
        return l;   
    }
}