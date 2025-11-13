import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDeg = new int[N + 1];
        List<Integer>[] G = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            G[from].add(to);
            inDeg[to]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) {
                q.offer(i);
                sb.append(i).append(' ');
                inDeg[i] = -1;
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : G[u]) {
                inDeg[v]--;
                if (inDeg[v] == 0) {
                    q.offer(v);
                    inDeg[v] = -1;
                    sb.append(v).append(' ');
                }
            }
        }

        System.out.println(sb);
        
    }
}