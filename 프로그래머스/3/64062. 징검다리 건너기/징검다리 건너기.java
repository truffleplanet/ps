/*	
2147483647
200000000

Brute-Force
시뮬레이션하기.
2억 * 20만 

2. k 이상의 빈구간 나올때까지 최솟값으로 뺄샘하기
 --> 먼저 pq에 모든 값 처박아둠. 
 --> 전역으로 누적 -값 계산해두기
 
 20만 * N(sigma(N)이 20만이 되는 N)

v = pq.poll
if (v <= global_M)
    cont

minus = v - global_M

for (arr) {
    arr[i] -= minus
    if (<=0 length >= k)
}

*/

/*
stones내의 k길이 구간 중 최댓값이 가장 작은 곳의 최댓값을 찾으면 된다.
슬라이딩하며 하면 O(N) 에 가깝게 가능

다만 최댓값을 어떻게 추적하냐의 문제가 있음. 
슬라이딩할 때 이전 값을 빼면서 최댓값 업데이트해야하는 상황이 있을 수 있음. 

treeset으로 해결

*/  
    

import java.util.*;

class Solution {
        
    public int solution(int[] stones, int k) {
        int l = 0;
        int r = 200000000;
        
        while (l <= r) {
            int mid = (l + r) / 2;
            
            int step = 0;
            boolean valid = true;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] - mid > 0) {
                    step = 0;
                } else {
                    step++;
                    if (step >= k) {
                        valid = false;
                        break;
                    }
                }
            }
            
            if (valid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return l;
        
    }
}


// import java.util.*;

// class Solution {
    
//     public int solution(int[] stones, int k) {
//         // 중복된건 지우면 안되는데 treeset은 해당 관리가 불가능함. 
//         final int MAX = 200_000_000; 
//         int[] count = new int[MAX + 1];
//         TreeSet<Integer> treeset = new TreeSet<>();
        
//         int ans = Integer.MAX_VALUE;
        
//         for (int i = 0; i < k; i++) {
//             treeset.add(stones[i]);
//             count[stones[i]]++;
//         }
        
//         ans = Math.min(ans, treeset.last());
        
//         for (int i = k; i < stones.length; i++) {
//             int x = i - k;
//             if (count[stones[x]] == 1) {
//                 treeset.remove(stones[x]);
//             }
//             count[stones[x]]--;
            
//             treeset.add(stones[i]);
//             count[stones[i]]++;
//             ans = Math.min(ans, treeset.last());
//         }
        
//         return ans;
//     }
        
// }

//         PriorityQueue<Integer> pq = new PriorityQueue<>();
//         for (int x : stones) {
//             pq.offer(x);
//         }
        
        
//         {
//         int step = 0;
//         for (int i = 0; i < stones.length; i++) {
//             if (stones[i] > 0) {
//                 step = 0;
//             } else {
//                 step++;
//                 if (step >= k) {
//                     return 0;
//                 }
//             }
//         }
//         }
        
//         int glob_M = 0;
//         boolean end = false;
//         int res = 0;

//         while (!pq.isEmpty()) {
            
//             int curMin = pq.poll();
            
//             if (curMin <= glob_M)
//                 continue;
            
//             int minus = curMin - glob_M;
//             glob_M += minus;
//             res += minus;
            
//             int step = 0;
//             for (int i = 0; i < stones.length; i++) {
//                 stones[i] -= minus;
                
//                 if (stones[i] > 0) {
//                     step = 0;
//                 } else {
//                     step++;
//                     if (step >= k) {
//                         end = true;
//                         break;
//                     }
//                 }
//             }
            
//             if (end)
//                 break;
//         }
    
        
//         return res;
//     