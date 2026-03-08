import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static final int MASK_7_BIT = 127;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        List<Integer>[] G = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }
        
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            G[parent].add(child);
            G[child].add(parent);
        }

        boolean[] visited = new boolean[N + 1];
        int[] q = new int[N];
        int tail = 0;
        int head = 0;
        visited[from] = true;
        q[tail++] = from << 7;
        
        while (head < tail) {
            int state = q[head++];
            int cnt = state & MASK_7_BIT;
            int u = (state >> 7);

            if (u == to) {
                System.out.println(cnt);
                return;
            }
            
            for (int v : G[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q[tail++] = (v << 7) | (cnt + 1);
                }
            }
        }

        System.out.println(-1);
        
    }
}