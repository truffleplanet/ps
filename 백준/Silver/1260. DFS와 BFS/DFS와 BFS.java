import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static StringBuilder sb;
    static List<Integer>[] G;
    static boolean[] visited;
    static int N;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        G = new List[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            G[u].add(v);
            G[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(G[i]);
        }

        dfs(start);
        sb.append('\n');
        bfs(start);

        System.out.println(sb);
    }

        static void dfs(int start) {
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] visited = new boolean[N + 1];
            visited[0] = true;
            stack.push(start);

            while (!stack.isEmpty()) {
                int cur = stack.pop();
                
                if (visited[cur]) 
                    continue;
                
                visited[cur] = true;
                sb.append(cur).append(' ');

                for (int i = G[cur].size() - 1; i >= 0; i--) {
                    int v = G[cur].get(i);
                    if (!visited[v]) {
                        stack.push(v);
                    }
                }
            }
        }
    
        static void bfs(int start) {
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[N + 1];
            visited[0] = visited[start] = true;
            sb.append(start).append(' ');
            q.offer(start);

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (int v : G[cur]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        sb.append(v).append(' ');
                        q.offer(v);
                    }
                }
            }
        }
}