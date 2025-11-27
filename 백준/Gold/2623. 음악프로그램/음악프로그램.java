/*
출연 순서를 정하기 위해 해야할 일.
보조 pd별 가수 출연 순서..

이 순서를 모아서 전체 가수의 순서를 정해야 함.
즉 모든 보조 피디의 순서가 참인 순서쌍을 만들어야 함. 

불가능하면 1, 가능하면 출연 순서 중 아무거나 하나를 출력하면 된다.

음.. 

즉 최종 수열에는 --> 
1. 모든 가수가 포함되어야 하고
2. 부분 수열에는 보조 피디들의 순서가 전부 들어있어야 함.

음.. 생각해보자.. 

1 -> 4 -> 3
6 -> 2 -> 5 -> 4
2 -> 3

들어오는 게 없는 것이 첫번째로 나와야한다.

위상 정렬 아이디어로 찾을 수 있음. 

그러면 못찾는 경우는?
--> 순환이 있는 case에는 못찾는다. 

순환이 있는 case는 어떻게 판별할까? 
입력 단에서 판단하냐 아니면 출력단에서 판단하냐?
입력단에서 판단하기는 어렵다. 양방향인 경우 외에도
사이클이 있으면 (2 -> 3, 3 -> 4, 4 -> 2)
그건 판단 못하기 때문.
출력 스택에 모든 인원이 쌓였는지 안 쌓였는지에 따라서 판단하자.

그런데 하나 더. 보조 피디 전체 목록에는 모든 가수 번호가 포함되는가? 
안된다면.. 마지막에 남은 인원들 붙여주면 되는데.
이게 순환 case 판별하는 쪽과 조건이 미묘하게 겹친다. 
그렇다면, 입력 시 나온 인원들을 boolean[] 하나 두고 카운트 해두자.
안나온 인원들은 스택에 미리 push하자. 
-> 이 방식은틀렸음. 안나온 인원의 indeg는 0 이기 때문에 indeg만 가지고 해도 된다.

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<Integer>[] G = new List[N + 1];
        for (int i = 1; i <= N; i++) {
        	G[i] = new ArrayList<>();
        }
        int[] inDeg = new int[N + 1];
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int K = Integer.parseInt(st.nextToken());
        	int prev = Integer.parseInt(st.nextToken());
        	K--;
        	while (K-- > 0) {
        		int next = Integer.parseInt(st.nextToken());
        		inDeg[next]++;
        		G[prev].add(next);
        		prev = next;
        	}
        }

        List<Integer> ans = new ArrayList<>();
        
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
        	if (inDeg[i] == 0) {
        		q.offer(i);
        	}
        }
        
        while (!q.isEmpty()) {
        	int cur = q.poll();
        	ans.add(cur);
        	for (int v : G[cur]) {
        		inDeg[v]--;
        		if (inDeg[v] == 0) {
        			q.offer(v);
        		}
        	}
        	
        }
        
        
        StringBuilder sb = new StringBuilder();
        if (ans.size() == N) {
        	for (int val : ans) {
        		sb.append(val).append('\n');
        	}
        } else {
        	sb.append('0');
        }
        
        System.out.println(sb);
        
    }
}