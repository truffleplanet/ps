/*
(N-1) * 100,000 이 최대 거리이니 int 사용 가능
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class E implements Comparable<E> {
        int to;
        int w;
        E parent;

        public E (int to, int w) {
            this.to = to;
            this.w = w;
        }

        public E (int to, int w, E parent) {
            this.to = to;
            this.w = w;
            this.parent = parent;
        }


        @Override
        public int compareTo(E o) {
            return this.w - o.w;
        } 
    }

    static List<E>[] G;
    static int V;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        G = new List[V + 1];
        for (int i = 1 ; i <= V; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            G[u].add(new E(v, w));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<E> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new E(start, 0));

        E last = null;
        while (!pq.isEmpty()) {
            E cur = pq.poll();

            if (dist[cur.to] != cur.w)
                continue;

            if (cur.to == end) {
                last = cur;
                break;
            }

            for (E e : G[cur.to]) {
                int nd = cur.w + e.w;
                if (dist[e.to] > nd) {
                    dist[e.to] = nd;
                    pq.offer(new E(e.to, nd, cur));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(last.w).append('\n');

        Deque<Integer> histStack = new ArrayDeque<>();

        while (last.parent != null) {
            histStack.push(last.to);
            last = last.parent;
        }

        histStack.push(start);

        sb.append(histStack.size()).append('\n');

        while (!histStack.isEmpty()) {
            sb.append(histStack.pop()).append(' ');
        }
        
        System.out.println(sb);
    }

}