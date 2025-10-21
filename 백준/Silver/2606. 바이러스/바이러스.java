/*
웜은 같은 네트워크의 모든 컴퓨터에 전파된다.

요구사항:
1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터를 출력하라

풀이:
모든 네트워크를 다 볼 필요는 없고, 1번 컴퓨터와 연결된 컴퓨터만 확인하면 됨.

주어지는 edge 그래프 상태로 입력 받으면서 바로 찾고 싶다면 disjoint set을 사용하면 되고
아니면, adjacency List로 입력 받은 후, dfs or bfs를 사용하여 연결된 노드의 수를 세어도 된다.

adjacencyList 사용하면 (2 * E) 만큼의 공간 사용하고
disjointSet 사용하면 (2 * V) 만큼의 공간을 사용한다.

parents, counts 배열  사용해서 구현해보기로 한다.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int[] parents;
    static int[] counts;

    static void init(int N) {
        parents = new int[N + 1];
        counts = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            counts[i] = 1;
        }
    }

    static int find(int x) {
        if (x == parents[x])
            return x;

        parents[x] = find(parents[x]);
        return parents[x];
    }

    static void union(int x, int y) {
        
        int root_x = find(x);
        int root_y = find(y);
        
        if (root_x != root_y) {

            if (root_x > root_y) {
                int temp = root_y;
                root_y = root_x;
                root_x = temp;
            } 
            parents[root_y] = root_x;
            counts[root_x] += counts[root_y];
        } // 반드시 작은 쪽으로 합쳐짐.
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        init(V);

        for (int i = 0; i < E; i++) {
            st =  new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        System.out.println(counts[find(1)] - 1); // 자기 자신 제외 
        
    }
}