/*
기존 4d arr 기반에서
미리 모든 쿼리에 대해 계산해두는 방식으로 변경
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    
    public int makeKey(int lang, int job, int career, int soulfood) {
        return (lang << 12) + (job << 8) + (career << 4) + soulfood;
    }
    
    public int lowerbound(List<Integer> lst, int score) {
        if (lst.isEmpty())
            return 0;
        int l = 0;
        int r = lst.size() - 1;
                 
        while (l <= r) {
            int mid = (l + r) / 2;
            if (lst.get(mid) < score) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    
    public int[] solution(String[] info, String[] query) throws Exception {
        HashMap<String, Integer> getKey = new HashMap<>();
        HashMap<Integer, List<Integer>> table = new HashMap<>();
        getKey.put("cpp", 1);
        getKey.put("java", 2);
        getKey.put("python", 3);
        getKey.put("backend", 1);
        getKey.put("frontend", 2);
        getKey.put("junior", 1);
        getKey.put("senior", 2);
        getKey.put("chicken", 1);
        getKey.put("pizza", 2);
        getKey.put("-", 0);
        
        for (int r1 = 0; r1 < 4; r1++) {
            for (int r2 = 0; r2 < 3; r2++) {
                for (int r3 = 0; r3 < 3; r3++) {
                    for (int r4 = 0; r4 < 3; r4++) {
                        table.put(makeKey(r1, r2, r3, r4), new ArrayList<>());
                    }
                }
            }
        }
        
        for (String data : info) {
            StringTokenizer st = new StringTokenizer(data);
            int[] lang = {0, getKey.get(st.nextToken())};
            int[] job = {0, getKey.get(st.nextToken())};
            int[] career = {0, getKey.get(st.nextToken())};
            int[] soulfood = {0, getKey.get(st.nextToken())};
            int score = Integer.parseInt(st.nextToken());
            for (int r1 : lang) {
                for (int r2 : job) {
                    for (int r3 : career) {
                        for (int r4 : soulfood) {
                            table.get(makeKey(r1, r2, r3, r4)).add(score);
                        }
                    }
                }
            }
        }
        
        for (int r1 = 0; r1 < 4; r1++) {
            for (int r2 = 0; r2 < 3; r2++) {
                for (int r3 = 0; r3 < 3; r3++) {
                    for (int r4 = 0; r4 < 3; r4++) {
                        Collections.sort(table.get(makeKey(r1, r2, r3, r4)));
                    }
                }
            }
        }
        
        int Q = query.length;
        int[] answer = new int[Q];
        for (int i = 0; i < Q; i++) {
            String q = query[i].replace(" and ", " ");
            StringTokenizer st = new StringTokenizer(q);
            int lang = getKey.get(st.nextToken());
            int job = getKey.get(st.nextToken());
            int career = getKey.get(st.nextToken());
            int soulfood = getKey.get(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            
            List<Integer> val = table.get(makeKey(lang, job, career, soulfood));
            answer[i] = val.size() - lowerbound(val, score);            
        }
        
        return answer;
    }
}