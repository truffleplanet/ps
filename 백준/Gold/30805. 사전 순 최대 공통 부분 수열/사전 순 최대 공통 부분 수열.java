/*
문제 정의:
사전 순으로 가장 큰 공통 부분 수열 찾기

정보:
1. 수열의 앞이 크면 사전 순으로 더 큼.

기본 아이디어:
다음 과정을 반복한다.
    1. 공통으로 보유한 가장 큰 수가 처음 등장하는 인덱스를 찾는다.
    2. 해당 수를 부분 수열에 추가한다.
    3. 1에서 찾은 인덱스부분까지를 각 수열에서 제외시킨다.
    4. 1부터 반복한다.

풀이:
위의 과정은 같은 정보를 얻기 위해 계속 반복되는 구간이 있다.
따라서 처음에 List<Integer> a_idx = new List[10], b_idx.. 을 만들고
각 a_idx[i] -> 에는 수열 a에 i가 등장하는 인덱스를 오름차순으로 삽입한다.

a_max, b_max는 각각 a,b의 최대 인덱스고 둘을 0으로 둔다.

a_idx[9..1], b_idx[9..1] 
9에서 lower bound binary search를 a_max, b_max 하고 존재하면 부분 수열에 추가,
a_max, b_max 업데이트 
반복.
그렇게 1까지 했는데 아무것도 존재하지 않으면 끝.

=> 이 풀이를 생각할때 조건을 잘못 읽고
숫자가 1~9인줄 알았다. 따라서 기본 아이디어 풀이가 더 빠를 것으로 예상하나.. 일단 ㄱ
*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] seqA = new int[N];
        List<Integer>[] indicesA = new List[101];
        for (int i = 1; i <= 100; i++) {
            indicesA[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            seqA[i] = v;
            indicesA[v].add(i);
        }

        int M = Integer.parseInt(br.readLine());
        int[] seqB = new int[M];
        List<Integer>[] indicesB = new List[101];
        for (int i = 1; i <= 100; i++) {
            indicesB[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(st.nextToken());
            seqB[i] = v;
            indicesB[v].add(i);
        }

        List<Integer> output = new ArrayList<>();
        int minA = 0;
        int minB = 0;

        while (minA < N && minB < M) {
            boolean updated = false;
            for (int i = 100; i >= 1; i--) {
                int keyA = Collections.binarySearch(indicesA[i], minA);
                int keyB = Collections.binarySearch(indicesB[i], minB);
                keyA = (keyA >= 0) ? keyA : -keyA - 1;
                keyB = (keyB >= 0) ? keyB : -keyB - 1;
                // System.out.println("i: " + i);
                // System.out.println(indicesA[i]);
                // System.out.println(indicesB[i]);

                if (keyA < indicesA[i].size() && keyB < indicesB[i].size()) {
                    output.add(i);
                    minA = indicesA[i].get(keyA) + 1;
                    minB = indicesB[i].get(keyB) + 1;
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                // System.out.println("minA: " + minA);
                // System.out.println("minB: " + minB);
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(output.size()).append('\n');
        for (int x : output) {
            sb.append(x).append(' ');
        }

        System.out.println(sb);
        
    }
}