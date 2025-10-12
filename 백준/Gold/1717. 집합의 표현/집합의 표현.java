import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[] parents;

    static void init(int n) {
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
    }
    
    static int find(int x) {
        if (x == parents[x])
            return x;
        parents[x] = find(parents[x]);
        return parents[x];
    }

    static void union(int x, int y) {
        int x_root = find(x);
        int y_root = find(y);

        if (x_root == y_root) {
            return;
        }

        parents[x_root] = y_root;
        return;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        final String YES = "YES";
        final String NO = "NO";

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        init(n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(a, b);
            } else if (cmd == 1) {
                int root_a = find(a);
                int root_b = find(b);

                if (root_a == root_b) {
                    sb.append(YES).append('\n');
                } else {
                    sb.append(NO).append('\n');
                }
            }
        }

        System.out.println(sb);
    }
}