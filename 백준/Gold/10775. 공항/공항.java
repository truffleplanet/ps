/*
각 비행기는 입장 순서대로 [1, g_i] 사이에 도킹 가능함
각자 [i, g_i] 사이에서 가장 큰 빈 게이트에 도킹하면 되는
그리디 접근 하면 최대로 도킹할 수 있을 것이다.

그런데 이걸
visited[G] 배열로 관리하면
최악 케이스의 경우
g_1 = 10^5
g_2 = 10^5
g_3 = 10^5
...
과 같은 식이면
1 + 2 + 3 + 4 + ... + 10^5가 되고
이는 O(G^2), G = 10^5 이므로
2초내 통과가 불가능하다 .

중복되는 탐색을 줄이기 위해 
disjoint set을 사용한다 

각 비행기는 도킹 시 가능하면 최대값에서 도킹하고, 도킹 후에는 자신의 왼쪽과 union한다
find를 했는데, 나온 값이 0이면 종료.
즉 union 시 parent는 항상 작은 값으로 둔다.
이미 union되어 있는 경우는 존재하지 않는다.

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N;
    static int[] parents;

    static void init() {
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    static int find(int x) {
        if (x == parents[x])
            return x;

        parents[x] = find(parents[x]);
        return parents[x];
    }

    static boolean union(int x) {
        int root_x = find(x);

        if (root_x == 0)
            return false;
        
        int y = root_x - 1;
        int root_y = find(y);

        parents[root_x] = root_y;

        return true;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        init();

        
        int P = Integer.parseInt(br.readLine());
        int ans = 0;
        for (int i = 0; i < P; i++) {
            int x = Integer.parseInt(br.readLine());
            if (union(x))
                ans++;
            else
                break;
        }
        System.out.println(ans);
    }
}