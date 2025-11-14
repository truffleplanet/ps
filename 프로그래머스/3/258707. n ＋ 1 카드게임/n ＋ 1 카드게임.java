/*
n+1이 되는 묶음은?
(1, n), (2, n - 1), (3, n - 2) .... (n/2, n - n/2)

1 - 12
2 - 11
3 - 10
4 - 9
5 - 8
6 - 7

테케 2.
1
2
3
4
처음에는 무조건 5, 8을 뽑아야 다음 라운드로 갈 수 있고,
2라운드에서는 살게 없어서 게임 종료

테케 3
5, 8, 1, 2
처음에는 5, 8을 내고
9, 4 는 사지 않고 넘어감
2라운드에 12, 11을 사고 12, 1
3라운드 11, 2를 냄
4라운드 종료.

음,, 일단 확실한 것은
현재 낼 것이 없고, 구매해서도 낼 것이 없으면 종료된다는 것임.

매턴 내내 2장씩 사더라도 매턴 2장을 내므로, 리스트의 길이는 항상 n / 3을 초과하지 않음
따라서 모든 턴에 현재 카드 리스트를 전부 순회해도 O(N^2)임

그런데, 어떤것을 사냐 마냐 판단하는 것은 좀더 복잡한 문제이다.
이 선택을 여기 무작정 곁들이면 2^n으로 시간복잡도가 폭발함.

1. 현재 기준으로 몇턴 다음까지 갈 수 있는지 판단한다. 이는 뽑기전 현재 카드로만 갈 수 있는 라운드.

2. 그리고, 그 범위에서 어떤 것을 구매하면 더 멀리 갈 수 있는지 판단한다. 한 턴이라도 더 가게 해주는 것이 있다면 선택하고,

3. 그 후 다시 스코프를 넓혀서, 뭘 사면 또 그 다음으로 갈 수 있는지 판단한다.

이 과정을 백트래킹으로 구현한다.

구체적인 구현:
1. 
리스트 내부를 순회하며 서로 짝지어서 n + 1이 되는 수가 있다면 바로 지우고,
갈 수 있는 라운드 수를 ++ 한다.
다만 이걸 리스트로 하면 삭제가 빈번해서 시간복잡도가 높아지니
음.. 뭐가좋을까?
Set으로 관리하고 contains(n + 1 - x) 이면 delete하는 식으로 하자.

2.
스코프 별로 고려할 수 있는 경우는 다음과 같다.
하나만 사면 되는 경우 
두 개를 사야만 다음 라운드로 갈 수 있는 경우

두개를 사야하는지 판단하는건 조합으로 하면 N^2이 되는데 
다만 스코프는 항상 작으니깐 최대가 (N/3)^2이 될 것이다. 

그러면.. 아 2개사는걸 판단해야하는게 정말 골치아프다. 
다만 2개를 사야만 하는 경우에는, 다른 두개를 사는 경우를 백트래킹해볼 이유는 없다

3. 이렇게 반복해서 하다보면 답이 나올 수 밖에 없긴 한데..
1개를 사도 되는 경우에 고려해야하는 것은, 이번 스코프에서 예를 들어 1개를 사서 해결이 됬으면,
다음 스코프에서는 그 1개 빼고 전부를 또 가진 카드와 비교해야하는데,
이러면 중복 연산이 계속 발생한다는 것이다. 

4. 
가진 카드, 구매를 보류한 카드, 현재 라운드에서 구매할 수 있는 카드
전자 두개를 개별적 set으로 만든다.

*/

import java.util.*;

class Solution {
    
    static int N;
    
    public int solution(int coin, int[] cards) {
        N = cards.length;
        HashSet<Integer> deck = new HashSet<>();
        HashSet<Integer> pended = new HashSet<>();
        
        int pairCnt = 0;
        int roundStart = N / 3;
        for (int i = 0; i < roundStart; i++) {
            if (checkPair(deck, cards[i])) {
                deck.remove(N + 1 - cards[i]);
                pairCnt++;
            } else {
                deck.add(cards[i]);
            }
        }
        
        int roundCnt = 0;
        
        while (pairCnt >= 0 && roundStart <= N) {
            roundCnt++;
            pairCnt--;
            
            if (roundStart == N)
                break;
            
            for (int i = roundStart; i <= roundStart + 1; i++) {
                if (coin > 0 && checkPair(deck, cards[i])) {
                    deck.remove(N + 1 - cards[i]);
                    coin--;
                    pairCnt++;
                } else {
                    pended.add(cards[i]);
                }
            }
            
            roundStart += 2;
                        
            if (pairCnt >= 0) {
                continue;
            }
            
            if (coin < 2) {
                break;
            }
            
            for (int val : pended) {
                if (checkPair(pended, val)) {
                    pended.remove(val);
                    pended.remove(N + 1 - val);
                    pairCnt++;
                    coin -= 2;
                    break;
                }
            }
        }
        
        return roundCnt;
    }
    
    public boolean checkPair(HashSet<Integer> deck, int val) {
        int target = N + 1 - val;
        if (deck.contains(target)) {
            return true;
        } else {
            return false;
        }
    } 
    
}