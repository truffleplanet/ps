/*
조건 1. 두 사람이 선물을 주고 받은 기록이 있다면 더 많이줬던 사람이 더 덜준 사람한테 받음
조건 2. 두 사람이 선물을 주고 받은 기록이 없거나 같다면, 선물 지수가 큰 사람이 더 작은 사람한테 하나 받음



*/

import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> idToidx = new HashMap<>();
        
        int N = friends.length;
        for (int i = 0; i < N; i++) {
            idToidx.put(friends[i], i);
        }
        
        int[][] giftTable = new int[N][N];
        int[] giftPoint = new int[N];
        
        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            int from = idToidx.get(st.nextToken());
            int to = idToidx.get(st.nextToken());
            giftTable[from][to]++;
            giftPoint[from]++;
            giftPoint[to]--;
        }
        
        int[] cnt = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (i == j)
                    continue;
                
                if (giftTable[i][j] > giftTable[j][i]) {
                    cnt[i]++;
                } else if (giftTable[i][j] < giftTable[j][i]) {
                    cnt[j]++;
                } else {
                    if (giftPoint[i] > giftPoint[j]) {
                        cnt[i]++;
                    } else if (giftPoint[i] < giftPoint[j]) {
                        cnt[j]++;
                    }
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, cnt[i]);
        }
    
        return ans;
    }   
}