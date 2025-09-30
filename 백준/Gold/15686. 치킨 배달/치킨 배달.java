/*
문제 정의:
1. 집과 최단 맨해튼 거리 치킨집의 거리가 치킨거리 
2. <도시의 치킨거리>는 모든 집의 치킨거리의 합이다.

제약 조건:
1. 치킨집의 개수는 최대 13 
2. M은 최대 13 (13 C 7이 최대로 조합 경우의 수 최대 약 1700)
3. 집의 개수는 최대 100

사전 사항:
1. 격자 그래프를 유지할 이유가 없으므로, 치킨집, 집을 각각 좌표로 관리.
2. 집 - 치킨집 사이의 맨해튼 거리는 미리 계산한다.

풀이:
1. 치킨집과 집의 좌표를 구하여 각각 리스트에 저장한다. 
2. 집의 개수 P와 치킨집의 개수 Q를 구한다. 
3. int[p][q] 크기의 배열을 선언하고 각 집과 치킨집의 맨해튼 거리를 구한다.
4. 치킨집 Q에서 M개만큼의 치킨집을 선택하는 모든 조합을 수행한다.
4-1. 조합이 완성되면 현재 상황에서 각 집의 치킨 거리를 구한다(최단 거리).
4-2. <도시의 치킨거리>를 구한다.
4-3. 최솟값을 갱신한다.
5. 출력.

시간복잡도:
1. 격자그래프 순회(데이터 읽기) - N^2
2. 테이블 생성 (P * Q)
3. 조합 ( Q C M) 최대 1700
4. 각 집의 치킨거리 계산 (P * Q)
5. <도시의 치킨거리 계산> (P)

1과 2는 단순 수행되고

3과 4+5는 곱해야 함.

최악 시 대략 

1700*(100*13 + 100) => 17만  

지배적인 계산이 십만대임. 끝.

*/

import java.util.*;
import java.lang.*;
import java.io.*;

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