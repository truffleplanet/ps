/*
풀이:
1. 진실을 들은 사람이 있는 파티에 있는 사람은 전부 진실을 들어야 한다.
2. 이를 재귀적으로 반복한 후, 더 이상 그 외 파티에 진실을 들은 사람이 없어야 한다.


방법:
빠른 연산을 위해 BitSet을 사용하기로 한다.
먼저 진실을 아는 사람 비트셋 knowers를 입력 데이터에 따라 초기화한다.
각 파티 입력도 BitSet으로 초기화하고 파티 리스트에 삽입한다.

while (진실을 아는 사람이 있는 새로운 파티가 있는가?) {
    for (파티 in 파티 전체) {
        if (진실을 아는 사람이 있는 파티) {
            knowers에 파티 삽입, remove 파티 
        }
    }
}

이 방식으로 하면 될텐데 지금 형식의 단점은 remove의 시간인데.
이를 극복하기 위해 LinkedList를 사용하고, Iterator의 remove를 사용하기로 한다. 

*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BitSet knowers = makeBitSet(br.readLine());
        List<BitSet> parties = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            BitSet party = makeBitSet(br.readLine());
            BitSet mask = (BitSet) knowers.clone();
            mask.and(party);
            boolean overlapped = !mask.isEmpty();
            if (overlapped) {
                knowers.or(party);
            } else {
                parties.add(party);
            }
        }

        boolean hasMore = true;
        while (hasMore) {
            hasMore = false;
            Iterator<BitSet> it = parties.iterator();
            while (it.hasNext()) {
                BitSet party = it.next();
                BitSet mask = (BitSet) knowers.clone();
                mask.and(party);
                boolean overlapped = !mask.isEmpty();
                if (overlapped) {
                    knowers.or(party);
                    it.remove();
                    hasMore = true;
                }
            }
        }

        System.out.println(parties.size());
    }

    static BitSet makeBitSet(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int k = Integer.parseInt(st.nextToken());
        BitSet out = new BitSet();
        for (int i = 0; i < k; i++) {
            out.set(Integer.parseInt(st.nextToken()));
        }
        return out;
    }

}

    
