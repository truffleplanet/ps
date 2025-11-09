/*
brute force
- insertion sort를 활용하면 
linked list든 arrayList든 매 회 그때의 array길이 O(N)
결과적으로 정수의 수 N이면 O(N^2) (1 + 2 + 3 + 4 +... + N)
통과 불가함.

삽입마다 바뀌는 중간값을 어떻게 추적할지,
그리고 삽입 + 중간값 조회의 시간 복잡도를 어떻게 줄일지가 문제임.

삽입을 했는데 삽입한 값이 중간값보다 크면? -> 중간값보다 작거나 같은 첫번째수가 중간값이 됨
삽입을 했는데 삽입한 값이 중간값보다 작으면 -> 중간값보다 크거나 같은 첫번째 수가 중간값이 됨.

이 아이디어를 이용하면..
홀수일때는 정확히 중앙을, 짝수일때는 중간의 작은수를 해야하니
Max Heap에는 중간값보다 작은 값들과 중간값을
Min Heap에는 중간값보다 큰 값들을 삽입한다.

두 힙의 크기는 차이가 없거나 1이어야함(홀수 시 Max Heap이 1 크게)
ex) 1, 5, 2, 10, -99, 7, 5
1.
1을 Max Heap에 삽입. 이게 중간값이 됨.

2. 5를 minHeap에 삽입. 여전히 Max Heap.peek() 이 중간값

3...

--> 삽입 시 peek()의 값을 보고 삽입하는게 아니라, 
조건 1. maxHeap.peek() <= minHeap.peek()
조건 2. maxHeap.size() >= minHeap.size()

두 조건이 성립해야 한다.
삽입 시점부터 항상 두 조건이 같을 수는 없다.
예를 들어 3, 1, 1이 삽입된다고 하면
처음에는 maxHeap에 3이 삽입되는데,
최종적으로는 maxHeap = {1, 1}, minHeap = {3}의 형태가 되어야 한다.

즉 교환을 구현해야한다.

단순화해보자.
교환은 양쪽의 peek()에서만 일어나면 된다는 점을 생각해서..
1. 삽입은 maxHeap과 minHeap의 사이즈만 비교해서 시작한다. 즉 조건 2만 성립하게 삽입한다.
2. 그 후 양쪽의 peek()을 비교하며 조건이 만족할때까지 교환한다.

시간복잡도: 
일단 매회 삽입해야하니 log(n)
교환은 한 회당 최대 1번이므로 log(n)
 최종: O(N * (log N))
N은 10만이고, 2^17이 10만대이니
대략 130만. * 상수 이고
0.1초 통과 가능

*/

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap.offer(10001);
        maxHeap.offer(-10001);

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());

            if (minHeap.size() < maxHeap.size()) {
                minHeap.offer(val);
            } else {
                maxHeap.offer(val);
            }

            while (minHeap.peek() < maxHeap.peek()) {
                int temp = minHeap.poll();
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(temp);
            }
            sb.append(maxHeap.peek()).append('\n');
        }

        System.out.println(sb);
    }
}