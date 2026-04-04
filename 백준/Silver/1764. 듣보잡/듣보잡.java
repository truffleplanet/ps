import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        List<String> dbo = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (set.contains(name)) {
                dbo.add(name);
            }
        }
        Collections.sort(dbo);

        StringBuilder sb = new StringBuilder();
        sb.append(dbo.size()).append('\n');
        for (String name: dbo) {
            sb.append(name).append('\n');
        }

        System.out.println(sb);
    }
}