/*

각 가방에는 한 개의 보석만을 담을 수 있다.
따라서 c_i < c_j 인 경우, j가방에는 i가방에 담을 수 있는 보석을 담을 수 있다.

그러면, 먼저 작은 가방에서, 고르고 그 이후 큰 가방에서 고르면 된다.

다만, 큰 가방에서의 최적 선택이 작은 가방에서 선택하지 않은 보석일 수도 있기 때문에
이를 기억해야 한다.
이걸 만약에 visited배열로 한다면 30만씩 매번 봐야하기 때문에 시간 초과가 난다.

따라서 보석은 무게순 오름차순 정렬을 하고 
pq는 가치 순 내림차순 정렬을 한다. 

크기가 작은 가방부터 
보석을 순회하며, 넣을 수 있는 것은 pq에 다 넣는다.
그 후, 최상위 값을 pq에서 꺼낸다. (이게 그 가방에 들어갈 보석)
다음 가방을 이전 보석 순회지점부터 순회한다.

이러면 이미 본 보석은 다시는 보지 않는다.
logN(pq 삽입시간) * N 이 시간 복잡도가 된다.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jewels = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] bagCapacities = new int[K];
        for (int i = 0; i < K; i++) {
            bagCapacities[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(bagCapacities);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int j= 0;
        long sum = 0;
        for (int i = 0; i < K; i++) {
            while (j < N && jewels[j][0] <= bagCapacities[i]) {
                pq.offer(jewels[j++][1]);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        System.out.println(sum);

    }
}