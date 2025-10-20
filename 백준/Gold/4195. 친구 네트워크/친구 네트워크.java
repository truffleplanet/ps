
import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static Map<String, Integer> name2id;
    static int N; // 관계의 수 (*이름의 수는 최대 2 * N
    static int regNo;
    static List<Integer> parents;
    static List<Integer> counts;

    static int find(int x) {
        if (parents.get(x) == x) {
            return x;
        }

        parents.set(x, find(parents.get(x)));

        return parents.get(x);
    }

    static int union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);

        if (root_x == root_y) {
            return counts.get(root_y);
        }

        parents.set(root_x, root_y);
        counts.set(root_y, counts.get(root_x) + counts.get(root_y));
        return counts.get(root_y);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int $ = 0; $ < T; $++) {

            N = Integer.parseInt(br.readLine());
            
            name2id = new HashMap<>();
            parents = new ArrayList<>();
            counts = new ArrayList<>();
            regNo = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                
                String u = st.nextToken();
                String v = st.nextToken();

                if (!name2id.containsKey(u)) {
                    name2id.put(u, regNo);
                    parents.add(regNo);
                    counts.add(1);
                    regNo++;
                }
                if (!name2id.containsKey(v)) {
                    name2id.put(v, regNo);
                    parents.add(regNo);
                    counts.add(1);
                    regNo++;
                }

                int u_id = name2id.get(u);
                int v_id = name2id.get(v);

                sb.append(union(u_id, v_id)).append('\n');
                 
            }
        }

        System.out.println(sb);
    }
}