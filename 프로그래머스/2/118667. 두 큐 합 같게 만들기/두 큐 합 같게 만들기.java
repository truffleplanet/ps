/*
조건:
삽입, 삭제를 합쳐서 1회의 작업으로 친다.
즉 두 큐간의 교환 1번이 1회이다.

풀이:
큐라는 자료구조의 특성을 잘 생각해보자.
어떤 N 크기의 큐에 뭘 삽입을 해도, pop N번의 결과는 정해져있다.

즉 큐 값을 교환할 때, 순서를 바꿔가며 찹찹찹한다해서 섞이는게 아니다. 
즉 양쪽의 마지막 원소만 한 큐로 옮긴다거나 이런게 불가능하다(q.size>2면)
그러면 모든 경우는? 매회 queue1에서 뽑을지 queue2에서 뽑을지 선택하며 꺼내는데
brute-force
총 N^2번 뽑을 수 있다 -- > 2 ^ (N^2)
불가하다.

[3, 2, 7, 2], sum: 14
[4, 6, 5, 1], sum: 16

[3, 2, 7, 2, 4], sum 20
[6, 5, 1], sum 12

[2, 7, 2, 4], sum 17
[6, 5, 1, 3], sum 15

[7, 2, 4], sum 15
[6, 5, 1, 3], sum 15


이런식인건데.. 셔플이 일어나지 않는 다는걸 생각하면 greedy접근도 괜찮아보이는데
증명할 수 있나?

일단 최대 이동횟수는 N번이다.
양 큐에 포인터 하나씩 두고,
그 지점 기준으로 잘라서 합 주고받는 식인것임/
근데 교환의 의미에서는 N번이면 된다.(같은 경우들이 중복됨)

다만 교환의 최대 횟수는 알아도
완탐은 N^2이다. 안돼~~

그러면..
그리디 해도 되는것인가?
될 것 같은데 증명이 되나?

Assumption: 큰 큐에서 작은 큐로 swap보다 빠른 경로(최적의 교환방법)이 있다.
sum1 < sum2라 할 때
sum1의 원소를 sum2로 넘겨주는 것이 최단경로일 수 있다라고 하면.
음.. 증명을 정확히 못하겠다.

그러면 
어차피 두 큐를 연결시킨 후 
그 큐 안에서 투 포인터안의 값과 밖의 값이 같아지는 지점 을 찾는 것이 목표임.
[a, b, c]
[d, e, f]
[a, |b, c, d|, e, f]

[i, j)를 합의 범위로 잡고 
그러면, pointer i는 0, j는 N에서 시작
각각 + 된 횟수가 교환횟수임. 
둘 다 증가하기만 하고,
i가 N까지 가면 종료.?(똑같은 짓 반복하는 것이니)
ㄱㄱ.
*/

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
        
        int[] concat = new int[2 * N];
        for (int i = 0; i < N; i++) {
            concat[i] = queue1[i];
        }
        for (int i = N; i < concat.length; i++) {
            concat[i] = queue2[i - N];
        }
        
        long sum = 0;
        for (int i = 0; i < concat.length; i++) {
            sum += concat[i];
        }
        
        if (sum % 2 != 0) {
            return -1;
        }
        
        long target = sum / 2;
        long cur = 0;
        for (int i = 0; i < N; i++) {
            cur += concat[i];
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0, j = N - 1; i < concat.length && j < concat.length - 1;) {
            if (cur > target) {
                cur -= concat[i];
                i++;
            } else if (cur < target) {
                j++;
                cur += concat[j];
            } else {
                ans = Math.min(ans, i + j - N + 1);
                break;
            }
        }
        
        if (ans == Integer.MAX_VALUE)
            return -1;
        
        return ans;
    }
}