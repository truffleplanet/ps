/*
[조건] + 코딩테스트 점수 x점 이상을 받은 사람
쿼리를 주면 카운팅해서 정답 내줘야함.

query가 10만개이고 
info가 5만개이므로
매 쿼리에서 info 전부 다 보는 식이면 효율성을 통과할 수 없음.

그런데 - 조건이 있으므로, 모든 조건 쌍에 대해 다 조회할 수 있게 잘 만들어두어야함.

어떤 자료구조로 저장해야할까?

['cpp']['backend']['junior']['>=100']
같은 쿼리를 생각해보자.
cpp이고 backend이고 junior인 사람들 점수만 따로 잘 모아두고
정렬해두었다면
100을 lowerbound search하고, size() - key 하면 된다.

4중 해쉬맵...??
은 눈이 힘들듯하니..
HashMap<String, Integer>에 
key를 미리 넣어두자
ex -> cpp 0, java 1 python 2
backend 0, frontend 1
junior = 0, senior 1

그러면 
List<Integer>[3][2][2] 로 표현 가능하고
- 키워드가 들어오면 거기 있는 리스트 전부 봐야하게 구현

*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {
    public int[] solution(String[] info, String[] query) throws Exception {
        HashMap<String, Integer> getKey = new HashMap<>();
        getKey.put("cpp", 0);
        getKey.put("java", 1);
        getKey.put("python", 2);
        getKey.put("backend", 0);
        getKey.put("frontend", 1);
        getKey.put("junior", 0);
        getKey.put("senior", 1);
        getKey.put("chicken", 0);
        getKey.put("pizza", 1);
        
        List<Integer>[][][][] table = new List[3][2][2][2];
        int N = 3;
        int M = 2;
        int P = 2;
        int K = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int p = 0; p < P; p++) {
                    for (int k = 0; k < K; k++) {
                        table[i][j][p][k] = new ArrayList<>();
                    }
                }
            }
        }

        
        for (String data : info) {
            String[] tokens = data.split(" ");
            int lang = getKey.get(tokens[0]);
            int job = getKey.get(tokens[1]);
            int career = getKey.get(tokens[2]);
            int soulfood = getKey.get(tokens[3]);
            int score = Integer.parseInt(tokens[4]);
            table[lang][job][career][soulfood].add(score);
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int p = 0; p < P; p++) {
                    for (int k = 0; k < K; k++) {
                        Collections.sort(table[i][j][p][k]);
                    }
                }
            }
        }
        
        int Q = query.length;
        int[] answer = new int[Q];
        for (int i = 0; i < Q; i++) {
            String[] tokens = query[i].split(" and ");
            String lang = tokens[0].trim();
            String job = tokens[1].trim();
            String career = tokens[2].trim();
            String[] ss = tokens[3].trim().split(" ");
            String soulfood = ss[0].trim();
            int score = Integer.parseInt(ss[1].trim());
            
            String[] qqq = {lang, job, career, soulfood};
            List<Integer>[] range = new List[4];
            for (int l = 0; l < 4; l++) {
                range[l] = new ArrayList<>();
            }
            for (int l = 0; l < 4; l++) {
                String token = qqq[l];
                if (token.equals("-")) {
                    if (l == 0) {
                        range[l].add(0);
                        range[l].add(1);
                        range[l].add(2);
                    } else {
                        range[l].add(0);
                        range[l].add(1);
                    }
                } else {
                    range[l].add(getKey.get(token));
                }
            }
            
            int total = 0;
            for (int c : range[0]) {
                for (int v : range[1]) {
                    for (int u : range[2]) {
                        for (int o : range[3]) {
                            int searchKey = lowerbound(table[c][v][u][o], score);
                            total += table[c][v][u][o].size() - searchKey;
                        }
                    }
                }
            }
            answer[i] = total;
            
        }
        return answer;
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
    
}