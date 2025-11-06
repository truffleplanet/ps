/*
일단 어떻게 풀어야할지 잘 모르겠지만
택배를 몇개실을지는 고민할 필요가 없음(그냥 땅에 버린다고 생각해도 됨)

어차피 깊은 곳까지 가야되는 횟수는 정해져있음.
한 회차에 할 수 있는 수량이 제한될 뿐임

그러면..
배달과
수거를 분리해서 생각해보자

배달만 만약에 한다면,
1step당
맨 뒤에 가장 먼 곳 왕복거리가 이동거리에 더해지고,
그 다음에 뒤부터 cap이소진될때까지 -하며 0으로 만듬
이를 모든 배달 배열이 0이 될때까지 반복.

수거도 마찬가지임. 

그런데 둘이 서로 영향을 끼치기는 하니깐, 수거타임, 배달 타임중 최댓값 이런 식으로는 안된다.

예를들어 수거가 1번집에 졸라 몰려잇고, 배달은 10만까지 한번만 갓다와도 된다고 하면
수거도 해야되고 배달도 해야되고

--> idea 수거 배달을 동시에 하되,
그 step에서 왕복 거리가 가장 큰 애를 더해준다
두 배열이 모두 소진되어야 끝난다.

시간복잡도. 만약
cap은 1이고 모든 배열이 50으로 차있다면?
50 + 51 + 52 + .... + 10만
10만^2으로 통과할 수가 없다..

조금만 줄일 수 없을까?
아 !
step이 아니라, 두 리스트를 동시 순회 하면서
순회 한번에
minus 계속 하면서 하면 될듯?
ㄱㄱ.
5,000,000 minus, 5,000,000 minus 실행하면 되니
천만.
*/

import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int dIdx = n - 1;
        int pIdx = n - 1;
        
        long ans = 0;
        
        while (dIdx >= 0 || pIdx >=0) {
            int dRes = cap;
            int pRes = cap;
            int curMax = -1;
            while (dIdx >= 0 && dRes > 0) {
                if (deliveries[dIdx] > 0) {
                    curMax = Math.max(curMax, dIdx);
                    dRes--;
                    deliveries[dIdx]--;
                } else {
                    dIdx--;
                }
            }
            
            while (pIdx >= 0 && pRes > 0) {
                if (pickups[pIdx] > 0) {
                    curMax = Math.max(curMax, pIdx);
                    pRes--;
                    pickups[pIdx]--;
                } else {
                    pIdx--;
                }
            }
            // 14 10 6. 30
            // System.out.println(Arrays.toString(deliveries));
            // System.out.println(Arrays.toString(pickups));
            
            if (curMax != -1)
                ans += (long) (curMax + 1) * 2;
        }
        
        
        return ans;
        
    }
}
