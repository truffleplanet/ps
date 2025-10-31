/*
아이디의 길이는 3자 이상 15자 이하여야 합니다.
아이디는 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 문자만 사용할 수 있습니다.
단, 마침표(.)는 처음과 끝에 사용할 수 없으며 또한 연속으로 사용할 수 없습니다.

1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.

['a' 'z'], '-', '_', '.'
*/


import java.util.*;

class Solution {
    public String solution(String new_id) {
        
        // 예외 
        if (new_id.length() == 0) {
            return "a";
        }
        
        // step 1
        new_id = new_id.toLowerCase();
        
        // step 2
        String parsed = "";
        for (char ch : new_id.toCharArray()) {
            if ((ch >= 'a' && ch <= 'z') || ch == '-' || ch == '_' || ch == '.' || (ch >= '0' && ch <= '9')) {
                parsed += ch;
            } 
        }
        
        // // 예외 
        // if (parsed.length() == 0) {
        //     return "a";
        // }

        
        // System.out.println(parsed)
        
        // step 3.
        
        String compressed = "";
        if (parsed.length() > 0) {
            char prev = parsed.charAt(0);

            for (int i = 1; i < parsed.length(); i++) {
                char cur = parsed.charAt(i);
                if (prev == '.' && prev == cur) {
                    continue;
                }            
                compressed += prev;
                prev = cur;
            }
            compressed += prev;
        }
        
        
        // System.out.println(compressed);
        
        // 예외 
        if (compressed.length() > 0) {
        

        
        // step 4

            if (compressed.charAt(0) == '.') {
                compressed = compressed.substring(1, compressed.length());
            }
            
        }

        // // 예외 
        if (compressed.length() > 0) {

            if (compressed.charAt(compressed.length() - 1) == '.') {
                compressed = compressed.substring(0, compressed.length() - 1);
            }
        }
        // step 5
        if (compressed.length() == 0) {
            compressed = "a";
        }
        
        // step 6
        if (compressed.length() >= 16) {
            compressed = compressed.substring(0, 15);
            
            if (compressed.charAt(compressed.length() - 1) == '.') {
                compressed = compressed.substring(0, compressed.length() - 1);
            }
        }
        
        //step 7
        
        if (compressed.length() <= 2) {
            char last = compressed.charAt(compressed.length() - 1);
            while (compressed.length() < 3) {
                compressed += last;
            }
        }
            
        return compressed;
    }
}