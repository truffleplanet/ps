import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static List<Integer>[] T;
    static int N;
    static int[] count;
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        T = new List[N + 1];
        count = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            T[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            T[u].add(v);
            T[v].add(u);
        }

        dfs(R, 0);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(count[query]).append('\n');
        }

        System.out.println(sb);
    }

    static int dfs(int cur, int parent) {
        count[cur] = 1;

        for (int v : T[cur]) {
            if (v != parent) {
                count[cur] += dfs(v, cur);
            }
        }

        return count[cur];
    }
}