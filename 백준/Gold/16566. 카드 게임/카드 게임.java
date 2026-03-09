/*
1. 파란색 빨간색 -- 즉 그냥 같은 번호 가진단 얘기임. 
2. 중복된 번호는 가질 수 없다.(정렬이 가능하다) 
3. 400만이면 좀 크긴 하니깐 nlogn피하기 위해 counting sort 가능 

4 - 철수가 낼 카드 입력으로 주어짐.  -> (철수의 카드는 덱의 상태를 타지 않는다)
5 - 민수가 낼 카드는 철수가 낼 카드보다 큰 카드 중 가장 작은 카드이다 --> 이진탐색
6 - K는 최대 1만이므로, shifting 비용을 피하려면 삭제를 효율적으로 해야함
7 - 우리는 항상 upperBound가 궁금하기 때문에, 어떤 수가 삭제되면 그 수와 가장 가까운 오른쪽 수와 매핑해주는게 필요함.
8 - 이건 유니온 파운드로 가능하다. 경로 압축 적용하면 
*/ 

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N, M, K;
    
    static int[] deck;
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boolean[] counting = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int val = Integer.parseInt(st.nextToken());
            counting[val] = true;
        }

        deck = new int[M];
        parent = new int[M + 1];
        int idx = 0;
        int val = 1;
        while (idx < M) {
            if (counting[val]) {
                deck[idx] = val;
                idx++;
            }
            val++;
        }
        for (int i = 1; i <= M; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int oppsCard = Integer.parseInt(st.nextToken());
            int myCardIdx = find(upperBound(oppsCard));

            sb.append(deck[myCardIdx]).append('\n');
            union(myCardIdx, myCardIdx + 1);
        }
        System.out.println(sb);
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

    static int upperBound(int x) {
        int l = 0;
        int r = M;

        while (l < r) {
            int mid = (l + r) >> 1;
            if (deck[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}