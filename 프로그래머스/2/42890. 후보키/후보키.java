/*
더 작은 부분 집합이 유일성-최소성 만족하면, 더 큰 집합은 후보키 될 수 없음


구현 필요한 것
1. 유일성 판단
2. 가능한 칼럼 조합 모두 하기 8C1 + 8C2 + 8C3 + ... 8C8
(작은 후보키부터 유일성이 판단되면 후보키 목록에 집어넣고 )
3. 후보키 목록에 자신의 부분집합이 있다면 x
4. 없다면 유일성 판단. 

어떤 데이터가 들어오든 유일성 판단하는 방법은..?
    데이터가 모두 string으로 들어오니
    String[row][선택된 칼럼 수] 만들어놓고 
    데이터 추가할 시 이전행과 모두 비교하며 값 전부 같으면 false

후보키 조합 뽑기
int N  전역변수 
comb(int cnt, int r) (N개 중에서 r개 뽑기)

for (i = 1 .. N) {
    comb(0, i)
}
로 후보키 순차적으로 뽑을 수 있음.


# 부분집합 판단 (최소성 )
칼럼 최대 8개이니 비트로 표현 가능함
11011 이 현재 키인데 후보키 집합에 11이 있다면?
11011 & 11 == 11 이면 false.

List<Integer> candidate -

ㄱㄱ

*/

import java.util.*;

class Solution {
    
    int N;
    int M;
    int R;
    List<Integer> candidate;
    boolean[] selected;
    String[][] relation;
    
    public void comb(int cnt, int start, int set) {
        if (cnt == R) {
            
            for (int mask : candidate) {
                if ((mask & set) == mask) {
                    return;
                }
            }
            
            if (isUnique(set)) {
                candidate.add(set);
            }
            
            return;
        }
        
        for (int i = start; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                comb(cnt + 1, i + 1, (set | (1 << i)));
                selected[i] = false;
            }
        }
    }
    
    public boolean isUnique(int set) {
        String[][] table = new String[M][N];
        
        for (String[] row : table) {
            Arrays.fill(row, "");
        }
        
        for (int j = 0; j < N; j++) {
            if ((set & (1 << j)) != 0) {
                table[0][j] = relation[0][j];
            }
        }
        
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                // 1 삽입
                if ((set & (1 << j)) != 0) {
                    table[i][j] = relation[i][j];
                }
                
                
                // 2 이전 행과 평가 
                for (int k = 0; k < i; k++) {
                    boolean same = true;
                    for (int c = 0; c < N; c++) {
                        if (!table[i][c].equals(table[k][c])) {
                            same = false;
                            break;
                        }
                    }
                    
                    if (same) {
                        return false;
                    }
                }

            }
        }
        
        return true;
    }
    
    public int solution(String[][] relation) {
        // System.out.println(1<<0);
        N = relation[0].length; // n-columns
        M = relation.length; //row
                
        candidate = new ArrayList<>();
        selected = new boolean[N];
        this.relation = relation;
        
        for (int i = 1; i <= N; i++) {
            R = i;
            comb(0, 0, 0);
        }
    
        return candidate.size();
    }
}