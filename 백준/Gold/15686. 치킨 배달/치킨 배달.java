/*
문제 정의:
1. 치킨거리는 치킨집과의 멘해튼 거리이다. 
2. <도시의 치킨거리>는 모든 집의 치킨거리의 합이다.

기본적인..:
격자 그래프를 유지할 이유가 없다. 
각 집과 치킨집을 점으로 유지한다.
M개의 치킨집을 고르는 문제..
먼저 조합은 안된다.(아주큰수 C 13)

집과 치킨집으로.. 치킨거리 테이블을 만드는건 당연히 가능하고..

뭘 고르는지 시뮬레이션할 때 일어나는일.

처음에는 모든 집이 거리 무한.
하나 고를 때마다 완화되긴하는데..
문제는 넣고 빼고 할때마다 완화된 이전 상태로 돌아가는게 집 숫자만큼 일어난다는 것임..
뭔가 전부 넣고 뺴고 하는 짓을 하는건 안되는데..  치킨집이 2500개의 셀에서 몇개나 있을 줄 알고..
-> 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.

13 개 중 13개 미만을 고르는거니깐 
다 해보면 되네 가장 큰 13C7 = 1716

-> 집의 최대 수 2N => 100
조합 한 후 모든 집과 거리 계산하는 비용 -> 100

맨해튼 거리는 미리 구해두자 그래도. 매번 재계산하지말고. 

100 * 1716 --> 몇십만대
ㄱㄱ
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    // const
    final static int HOUSE = 1;
    final static int CHICKEN = 2;

    // vars
    static int N;
    static int M;
    static int houseCnt;
    static int kfcCnt;
    static int ans;
    static List<Pos> houseList;
    static List<Pos> kfcList;
    static boolean[] selected;
    static int[][] distTable;
    
    static class Pos {
        int r;
        int c;

        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static int manDist(Pos a, Pos b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }

    public static void comb(int start, int cnt) {
        if (cnt == M) {
            int[] minDist = new int[houseCnt];
            Arrays.fill(minDist, Integer.MAX_VALUE);
            for (int i = 0 ; i < houseCnt; i++) {
                for (int j = 0; j < kfcCnt; j++) {
                    if (selected[j] && minDist[i] > distTable[i][j]) {
                        minDist[i] = distTable[i][j];
                    }
                }
            }
            int sum = 0;
            for (int i = 0; i < houseCnt; i++) {
                sum += minDist[i];
            }
            ans = Math.min(ans, sum);
            return;
        }

        for (int i = start; i < kfcCnt; i++) {
            if (!selected[i]) {
                selected[i] = true;
                comb(i + 1, cnt + 1);
                selected[i] = false;
            }
            
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houseList = new ArrayList();
        kfcList = new ArrayList();
        ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == HOUSE) {
                    houseList.add(new Pos(i, j));
                } else if (val == CHICKEN) {
                    kfcList.add(new Pos(i, j));
                }
            }
        }
        
        houseCnt = houseList.size();
        kfcCnt = kfcList.size();
        M = Math.min(M, kfcList.size());
        distTable = new int[houseCnt][kfcCnt];
        selected = new boolean[kfcCnt];
        
        for (int i = 0; i < houseCnt; i++) {
            Pos house = houseList.get(i);
            for (int j = 0; j < kfcCnt; j++) {
                Pos kfc = kfcList.get(j);
                distTable[i][j] = manDist(house, kfc);
            }
        }

        comb(0, 0);
        System.out.println(ans);
        
    }

    
}