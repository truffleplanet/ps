/*
operation이 10만개이고,
ShiftRow는 O(1)에 가능 (포인터만 옮기기, deque)
Rotate는 .. 그냥 하면 R * C * 2인데 r * c는 최대 100,000
그냥 하면 시간 초과 발생.

Rotate를 몰아서 할 방법이 없을까?
shiftRow한다고, rotate에 영향 가나? 
가지요.

음... rotate도 일차원 deque로 생각하면 O(1)이긴 한데
이걸 행렬에 업데이트 해줘야하니깐 2*r*c가 나오긴 한다.

Deque<int[]>로 전체 행렬 관리하면 ShiftRow는 O(1)

Deque<Integer>로 외곽 관리하면 Rotate는 O(1)

그런데 이 둘을 정합해줘야함. 그 과정이 결국에 O(R*C) 인데.
이럴거면 외곽은 굳이 Deque로 관리할 필요가 없다. 등록하는데에 비용이 또 드니깐

저격 테케 무시하고, Rotate이어지다가, ShiftRow할때만 정합해주면 전체적인
복잡도가 줄긴 한다.

그러나 교차로 등장하면 어쨌든
O(R*C) * 100,000/2 이므로.
5,000,000,000 50억..


행과 열을 각각 저장한다면..?
자바 Deque에는 구현되어있지 않지만, rotate 구현해서 head만 움직이게 하면,
ShiftRow의 시간복잡도는 O(1)

각 행도 전부 deque이면
Rotate시 시간 복잡도는 O(1) + O(R) (그런데 열도 따로 저장되어있어야 이게 되는데.)

열 저장과 행 저장을 따로 하면..
shiftRow시 시간복잡도 -> O(C) (회전 가능한 deque이면) O(1)

Rotate시 시간복잡도 O(C) + O(1)
여전히 문제가 있다. 

열 저장 행 저장을 같이 하는데 업데이트도 빠르게 할 방법 없을까?..
아!! 열은 양 끝만 저장해도 되지 않음>???
Rotate할 때만 값 자체가 변하는데
그럼 ShiftRow할 때마다 무식하게 업데이트 해도 됨.

맨 마지막에.. 모든게 끝났을 때 그러면?

ㄱㄱ
*/

import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        final String SHIFT_CMD = "ShiftRow";
        int H = rc.length;
        int W = rc[0].length;
        
        Deque<Integer> leftCol = new ArrayDeque<>();
        Deque<Integer> rightCol = new ArrayDeque<>();
        for (int i = 0; i < H; i++) {
            leftCol.offerLast(rc[i][0]);
            rightCol.offerLast(rc[i][W - 1]);
        }
        
        Deque<Deque<Integer>> matrix = new ArrayDeque<>();
        for (int i = 0; i < H; i++) {
            Deque<Integer> row = new ArrayDeque<>();
            for (int j = 0; j < W; j++) {
                row.offerLast(rc[i][j]);
            }
            matrix.offerLast(row);
        }
        
        for (String oper : operations) {
            if (oper.equals(SHIFT_CMD)) {
                shiftRow(matrix, leftCol, rightCol);
            } else {
                rotate(matrix, leftCol, rightCol);
            }
        }
        
        int[][] answer = new int[H][W];
        for (int i = 0; i < H; i++) {
            answer[i][0] = leftCol.removeFirst();
            answer[i][W - 1] = rightCol.removeFirst();
        }
        
        for (int i = 0; i < H; i++) {
            Deque<Integer> row = matrix.removeFirst();
            row.removeFirst();
            row.removeLast();
            for (int j = 1; j < W - 1; j++) {
                answer[i][j] = row.removeFirst();
            }
        }
        
        return answer;
    }
    
    public void shiftRow(Deque<Deque<Integer>> matrix, Deque<Integer> l, Deque<Integer> r) {
        matrix.offerFirst(matrix.removeLast());
        l.offerFirst(l.removeLast());
        r.offerFirst(r.removeLast());
        Deque<Integer> last = matrix.getLast();
        last.removeFirst();
        last.removeLast();
        last.offerFirst(l.getLast());
        last.offerLast(r.getLast());
    }
    
    public void rotate(Deque<Deque<Integer>> matrix, Deque<Integer> leftCol, Deque<Integer> rightCol) {
        Deque<Integer> firstRow = matrix.getFirst();
        Deque<Integer> lastRow = matrix.getLast();
        
        // 첫 행에서 맨 끝 요소를 삭제(이건 필요 없음)
        firstRow.removeLast();
        rightCol.offerFirst(firstRow.getLast());
        
        // 마지막 열에서 끝 요소를 빼둔다.
        rightCol.removeLast();
        // 
        lastRow.offerLast(rightCol.getLast());
        // 마지막 행에서 첫 요소를 빼둔다
        lastRow.removeFirst();
        
        leftCol.offerLast(lastRow.getFirst());
        leftCol.removeFirst();
        
        firstRow.offerFirst(leftCol.getFirst());
    }
    
}