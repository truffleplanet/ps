import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        int M = gifts.length;
        StringTokenizer st;
        
        Map<String, Integer> toInt = new HashMap<>();
        int[][] adjMatrix = new int[N][N];
        int[] pDegree = new int[N];
        int[] nMonth = new int[N];
         
        for (int i = 0; i < N; i++) {
            toInt.put(friends[i], i);
        } 
        
        for (int i =0; i < M; i++) {
            st = new StringTokenizer(gifts[i]);
            int from = toInt.get(st.nextToken());
            int to = toInt.get(st.nextToken());
            adjMatrix[from][to]++;
            pDegree[from]++;
            pDegree[to]--;
        }
        
        for (int i =0; i < N-1; i++) {
            for (int j = i +1; j < N; j++) {
                int itoj = adjMatrix[i][j];
                int jtoi = adjMatrix[j][i];
                if (itoj > jtoi) {
                    nMonth[i]++;
                } else if (itoj < jtoi) {
                    nMonth[j]++;
                } else {
                    if (pDegree[i] > pDegree[j]) {
                        nMonth[i]++;
                    } else if (pDegree[i] < pDegree[j]) {
                        nMonth[j]++;
                    } 
                }
            }
        }
        int answer = 0;
        for (int i =0; i < N; i++) {
            answer = Math.max(answer, nMonth[i]);
        }
        
        return answer;
    }
}