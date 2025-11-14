/*
전깃줄이 교차하지 않는다는 것은
[a] -> [b]의 연결이 엄격한 오름차순이 되어야한다는 것이다. 

연결을 먼저 [a] 기준으로 정렬한다.
그 후 [b]의 LIS를 구한다. 
LIS를 구할 때 사용되지 않는 [a] -> [b] 연결을 식별하고
그 [a]를 차례대로 출력해야 한다.

세부 구현:
1. 먼저 a -> b 연결을 표현할 클래스 Point를 구현한다. Comparable을 구현하여 
a 기준으로 정렬될 수 있도록 한다.

-> 사용되는 a값을 기록해둔다. 전깃줄의 개수에 비해 위치의 번호가 크므로
array로 희소표현 하지 않고,treeset으로 관리한다.

2. 
리스트에서 b를 기준으로 lis를 구한다.
lis는 역추적할 수 있게 dpIdx, ancestorIdx를 기록해둔다. 

3. 
lis를 역추적하며, lis에 사용된 a값들을 treeset에서 제거한다.
이후 남은 값들을 출력한다.

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Connection {
        int a;
        int b;

        public Connection (int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "a: " + a + ", b: " + b;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        TreeSet<Integer> aSet = new TreeSet<>();

        
        Connection[] connections = new Connection[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connections[i] = new Connection(a, b);
            aSet.add(a);
        }

        Arrays.sort(connections, (o1, o2) -> (o1.a - o2.a));

        List<Integer> LIS = new ArrayList<>();
        int[] dpIdx = new int[N];
        int[] ancestor = new int[N];
        LIS.add(connections[0].b);
        dpIdx[0] = 0;
        ancestor[0] = -1;

        for (int i = 1; i < N; i++) {
            int b = connections[i].b;
            if (b > LIS.get(LIS.size() - 1)) {
                LIS.add(b);
                dpIdx[LIS.size() - 1] = i;
                ancestor[i] = dpIdx[LIS.size() - 2]; // 여기가 문제다 
                continue;
            }

            /*
            Lis [8]
            Lis [2]
            LIS [2, 9]
            LIS [1, 9]
            LIS [1, 4]
            LIS [1, 4, 6]
            ... [1, 4, 6, 7, 10]

            ANCESTOR [-1]
            ANCESTOR [-1, -1]
            ANCESTOR [-1, -1, 1]
            ANCESTOR [-1, -1, 1, ] //이런식으로 되어야하는데..? 
            */
            
            int insPoint = lowerBound(LIS, b);
            LIS.set(insPoint, b);
            dpIdx[insPoint] = i;
            if (insPoint > 0) {
                ancestor[i] = dpIdx[insPoint - 1]; //
            } else {
                ancestor[i] = -1;
            }
        }

        // int x = 0;
        // for (Connection connection : connections) {
        //     System.out.println("idx: " + x + ", " + connection);
        //     x++;
        // }
        // System.out.println();
        // System.out.println(LIS);
        // System.out.println(Arrays.toString(dpIdx));
        // System.out.println(Arrays.toString(ancestor));

        // 역추적 Z
        int pos = 0;
        for (int i = 1; i < dpIdx.length; i++) {
            if (dpIdx[i] > 0) {
                pos = dpIdx[i];
            }
        }

        
        aSet.remove(connections[pos].a);
        while (ancestor[pos] != -1) {
            pos = ancestor[pos];
            aSet.remove(connections[pos].a);
        }
        // 10 -> 7 -> 5 -> 4 -> 8  
        // LIS가 잘못 나오고 있음.
        // 이유는 ancestor가 제대로 기록되지 있지 않아서.. 이유는?
        StringBuilder sb = new StringBuilder();
        sb.append(aSet.size()).append('\n');

        for (int val : aSet) {
            sb.append(val).append('\n');
        }

        System.out.println(sb);
        
    }

    public static int lowerBound(List<Integer> list, int key) {
        int l = 0;
        int r = list.size();

        while (l < r) {
            int mid = (l + r) / 2;

            if (key < list.get(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    
    }
    
}
