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
    
    public int[] solution (String s) {
        
        String[] stringSet = s.replaceAll("[{}]", " ").trim().split(" , ");
        
        List<Integer>[] intSets = new List[stringSet.length];
        
        for (int i = 0; i < stringSet.length; i++) {
            intSets[i] = new ArrayList<>();
            String[] tokens = stringSet[i].split(",");
            for (String token : tokens) {
                intSets[i].add(Integer.parseInt(token));
            }
        }
        
        Arrays.sort(intSets, Comparator.comparingInt(x -> x.size()));
        
        HashSet<Integer> ans = new LinkedHashSet<>();
        for (List<Integer> set : intSets) {
            for (int v : set) {
                ans.add(v);
            }
        }
        
        int[] out = new int[ans.size()];
        int i = 0;
        for (int v : ans) {
            out[i] = v;
            i++;
        }
                
        return out;
//         List<String> sets = new ArrayList<>();
//         char[] ca = s.toCharArray();
        
//         int l = 1;
//         for (int i = 1; i < ca.length; i++) {
//             if (ca[i] == '}') {
//                 sets.add(s.substring(l, i));
//                 l = i + 3;
//             }
//         }
        
//         List<IntSet> intsets = new ArrayList<>();
//         for (String set : sets) {
//             String[] tk = set.split(",");
//             List<Integer> vals = new ArrayList<>();
//             for (String token : tk) {
//                 vals.add(Integer.parseInt(token));
//             }
//             IntSet x = new IntSet(vals);
//             intsets.add(x);
//         }
        
//         Collections.sort(intsets);
        
//         List<Integer> ans = new ArrayList<>();
//         boolean[] visited = new boolean[100001];
//         for (IntSet p : intsets) {
//             for (int v : p.vals) {
//                 if (!visited[v]) {
//                     visited[v] = true;
//                     ans.add(v);
//                     break;
//                 }
//             }
//         }
        
        
//         int[] answer = new int[ans.size()];
//         for (int i = 0; i < ans.size(); i++) {
//             answer[i] = ans.get(i);
//         }
//         return answer;
    }
}