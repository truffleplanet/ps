/*
조건
1. 먼저 푸는 것이 좋은 문제가 있다면, 먼저 풀어야 함.
2. 가능하면 쉬운 문제부터 풀어야 함.
3. 문제는 난이도 순으로 되어있음. 

다르게 말하면
매 시점에서 풀 수 있는 문제는 다음과 같은 조건을 가진다.
1. 먼저 풀어야할 문제가 없는 문제여야 하고
2. 그 중 난이도가 가장 낮아야 함.

위상정렬 + pq

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] G = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }
        int[] inDeg = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            G[from].add(to);
            inDeg[to]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) {
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int u = pq.poll();
            sb.append(u).append(' ');

            for (int v : G[u]) {
                inDeg[v]--;
                if (inDeg[v] == 0) {
                    pq.add(v);
                }
            }
        }

        System.out.println(sb);
    }
}