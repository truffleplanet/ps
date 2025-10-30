/*
1. 
패턴 매칭함수 만들어서, 가능한 경우 true, 아니면 false 반환해서
어떤 user_id에 가능한지 구하는 것은 간단함. 

2. banned[1] -> 1, 3 ,5 가능
   banned[2] -> 2, 3, 5 가능이라면? 
   가능한 순열 전부 어떻게 구하는가? 
   
   1, 3
   1, 5
   3, 2
   3, 5
   
   ...
   
   그러면 
   pos = new boolean[banned_id.length][user_id.length]
   하고.. 
   selected boolean[8] 써서.. 
   
   음..
   재귀를 어떻게 하지?
   permu (int cnt) {
   if (cnt == N)
      ans++;
   }
   
   제재 아이디 목록들을 구했을 때 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리하여 하나로 세면 됩니다.

아이디 idx가 동일한 목록이면 하나로 세야함.

여러 int 묶음을 key로 카운트할 방법은..?
최대 8이니 비트 밀어서.. Hash로 카운팅.

*/

import java.util.*;

class Solution {
    
    int ans;
    int N;
    int M;
    boolean[] selected;
    boolean[][] pos;
    HashSet<Integer> visited;

    public boolean match(String x, String masked) {
        if (x.length() != masked.length())
            return false;
        
        for (int i = 0; i < masked.length(); i++) {
            if (masked.charAt(i) == '*')
                continue;
            if (masked.charAt(i) != x.charAt(i))
                return false;
        }
        
        return true;
    }
        
    public void permu(int cnt, int key) {
        if (cnt == M) {
            if (!visited.contains(key)) {
                visited.add(key);
                ans++;
            }
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (pos[cnt][i]) {
                if (!selected[i]) {
                    selected[i] = true;
                    int nkey = (key | (1 << i));
                    permu(cnt + 1, nkey);
                    selected[i] = false;
                }
             }
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {        
        ans = 0;
        N = user_id.length;
        M = banned_id.length;
        selected = new boolean[N];
        pos = new boolean[M][N];
        visited = new HashSet<>();
        
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (match(user_id[j], banned_id[i])) {
                    pos[i][j] = true;
                }
            }
        }
                
        permu(0, 0);
        
        return ans;
    }
}