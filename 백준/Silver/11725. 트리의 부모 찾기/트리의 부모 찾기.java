import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < n - 1; i++) {
            String[] tokens = br.readLine().split(" ");
            int u = Integer.parseInt(tokens[0]);
            int v = Integer.parseInt(tokens[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Deque<Integer> stack = new LinkedList<>();
        int[] tree = new int[n + 1];

        for (int v : graph.get(1)) {
            tree[v] = 1;
            stack.push(v);
        }

        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v : graph.get(u)) {
                if (tree[v] == 0) {
                    tree[v] = u;
                    stack.push(v);
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(tree[i]);
        }

    }
}
