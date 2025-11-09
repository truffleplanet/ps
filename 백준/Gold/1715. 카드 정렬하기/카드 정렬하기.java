/*
혀프만 코딩과 같은 그리디이다.
가장 작은 것끼리 합해서 하나의 클러스터로 묶어줘야한다.
https://truffleplanet.tistory.com/4

누적해서 합해지므로 long을 사용한다. (전부 1000이고, 100,000 묶음이면)..
총 합치기는 N-1수행되니,
1000 * (99,999) + 1000 * (99,998) + .... + 1000 * (1)인데.
1000 * 99,999 * (100,000/2) = 4,999,950,000,000
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Long> pq = new PriorityQueue<>(); 

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.offer(Long.parseLong(br.readLine()));
        }

        long ans = 0;
        while (pq.size() > 1) {
            long sum = pq.poll() + pq.poll();
            ans += sum;
            pq.offer(sum);
        }

        System.out.println(ans);
    }
}