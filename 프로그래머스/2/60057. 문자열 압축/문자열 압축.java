/*
자르는 단위가 1일때는 걱정없고

2개 이상으로 자를때는 
ababa
같은 경우 2aba로도 쓸수도 있고 a2ba로 쓸수도 있다

각 단위에서 최소가 되게 압축할 필요가 있을듯한데..
이걸 어떻게 하지?

abcabcab
2abcab
a2bcab
ab2cab

---> 
문제 설명: 문자열을 1개 이상의 단위로 잘라서 압축하여 더 짧은 문자열로 표현할 수 있는지 방법을 찾아보려고 합니다.

잘 읽자.. 고정 길이로 무조건 쪼개놓은 후 인접한 같은 것끼리 압축하는 것임.

그러면 어느 단위까지 잘라봐야할까?
최대 2 / N 까지

압축은
cur != prev
 then 
    if (count > 2) then len += (1 + split_size)
    else then len += split_size? 가 아니라 조각 사이즈 더해야함. (끝 부분 고려)
    count = 1

cur == prev
    then count++

구현 고민
1. 매번 substring으로 자르기 vs 진짜로 잘라버린 token배열 가지고 처리하기
-> 부담 크게 없으니 진짜 자른 배열 가지고 하자. 
*/

import java.util.*;

class Solution {
    public int solution(String s) {
        int N = s.length();
        
        int maxSplitSize = N / 2 + 1;
        int ans = N;
        
        for (int splitSize = 1; splitSize <= maxSplitSize; splitSize++) {
            
            List<String> tokens = split(s, splitSize);
            // System.out.println(tokens);
            String prev = tokens.get(0);
            int len = 0;
            int count = 1;
            
            for (int i = 1; i < tokens.size(); i++) {
                String cur = tokens.get(i);
                if (!cur.equals(prev)) {
                    if (count > 1) {
                        len += (String.valueOf(count).length() + splitSize);
                    } else {
                        len += prev.length();
                    }
                    count = 1;
                } else {
                    count++;
                }
                prev = cur;
            }
            
            // 처리못한 마지막 파트 처리하기 
            if (count > 1) {
                len += (String.valueOf(count).length() + splitSize);
            } else {
                len += prev.length();
            }
            
            ans = Math.min(ans, len);
        }
        
        return ans;
    }
    
    public List<String> split(String s, int splitSize) {
        List<String> out = new ArrayList<>();
        int N = s.length();
        int start = 0;
        while (start < N) {
            int end = Math.min(start + splitSize, N);
            out.add(s.substring(start, end));
            start += splitSize;
        }
        return out;
    }
}