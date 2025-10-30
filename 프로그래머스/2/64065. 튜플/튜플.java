/*
중복되는 원소가 없는 튜플에 대한 집합 표현들이 주어짐.

이 때 순서는 어떻게 파악하지?


집합은 {a1,} 부터 차례대로.. 
{a1, a2} {a1, a2, a3} 이런식으로
커지면 더 뒤의 원소들이 포함됨

그러면.. 길이 순 정렬하고, 이전에 안나온것부터 보면 되겠다.

파싱..
모든 {, }를 제거한 후 ,를 구분자로 


*/
import java.util.*;
import java.lang.*;

class Solution {
    
    class IntSet implements Comparable<IntSet> {
        List<Integer> vals;
        int len;
        
        public IntSet(List<Integer> lst) {
            this.vals = lst;
            this.len = lst.size();
        }
        
        @Override
        public int compareTo(IntSet o) {
            return this.len - o.len;
        }
    }
    
    public int[] solution (String s) throws Exception {
        
        s = s.substring(1, s.length() - 1);
        
        List<String> sets = new ArrayList<>();
        char[] ca = s.toCharArray();
        
        int l = 1;
        for (int i = 1; i < ca.length; i++) {
            if (ca[i] == '}') {
                sets.add(s.substring(l, i));
                l = i + 3;
            }
        }
        
        List<IntSet> intsets = new ArrayList<>();
        for (String set : sets) {
            String[] tk = set.split(",");
            List<Integer> vals = new ArrayList<>();
            for (String token : tk) {
                vals.add(Integer.parseInt(token));
            }
            IntSet x = new IntSet(vals);
            intsets.add(x);
        }
        
        Collections.sort(intsets);
        
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[100001];
        for (IntSet p : intsets) {
            for (int v : p.vals) {
                if (!visited[v]) {
                    visited[v] = true;
                    ans.add(v);
                    break;
                }
            }
        }
        
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}