/*
문제 정의:
주어진 수열의 특정 구간이 펠린드롬인지 아닌지 판단하는 오프라인 쿼리 문제.

풀이:
단순 구현으로 펠린드롬을 판단하려면 2/N만큼의 시간이 소요됨.
따라서 1,000 * 1,000,000으로 시간 초과.

미리 무언가 계산해두고 쿼리에 답변해야함.
어떤 구간이 펠린드롬이 절대 아니라는 것을 미리 알아둘 방법이 있나.?
펠린드롬. 중앙에서부터 보면 좌우가 같아야하고(홀수)
좌우부터 보면 양끝이 계속 같아야함.

아이디어 1.
홀수 입력에 대해서는, 펠린드롬이 될 수 없는 중심점들을 미리 구해두면,
빨리 답해줄 수 있다. 
--> 기존 brute force와 차이 없음.

아이디어2.
접두사와 접미사가 일치해야 펠린드롬
뭔가 문자열 패턴매칭 알고리즘으로, 가능한 펠린드롬을 미리 다 구하고 그 범위를 Map에 저장해두기
--> 그런건 모르겠다.

아이디어3.
홀수일 때를 기준으로 먼저 생각해서, 모든 점을 중심점으로 생각하고, 펠린드롬 구해서 Map에 저장하기
O(N^2)
짝수인 경우도 마찬가지로, 연속된 수가 같을 때만, 그 둘을 중심점으로 해서 펠린드롬 전부 구하고 Map에 저장하기
O(N^2)

쿼리에 답하기.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Set<Integer> pelindromes = new HashSet<>();
        
        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        // 홀수 경우 계산.
        for (int i = 0; i < N; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < N) {
                if (seq[l] != seq[r]) 
                    break;
                pelindromes.add(getKey(l + 1, r + 1));
                l--;
                r++;
            }
        }

        for (int i = 0; i < N - 1; i++) {
            if (seq[i] != seq[i + 1])
                continue;
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < N) {
                if (seq[l] != seq[r]) 
                    break;
                pelindromes.add(getKey(l + 1, r + 1));
                l--;
                r++;
            }
        }


        // query
        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (pelindromes.contains(getKey(l, r))) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
        }

        System.out.println(sb);
        
    }

    public static int getKey(int i, int j) {
        return (i << 12) + j;
    } 
}