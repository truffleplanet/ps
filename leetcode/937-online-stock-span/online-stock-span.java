/*
brute force O(N^2)
- 전부 저장해두고 매번 역순으로 더 큰 것이 나올 때 까지 읽기

LIS 아이디어를 활용할 수 없을까?

idea - 나보다 큰 값을 가진 key를 이진탐색으로 찾기

1. 정방향 정렬을 해서 idx를 저장해둔다. 
[100, 80, 60, 70, 60, 75, 85]
  0   1    2   3  4   5   6

- []
- [0]
- [1, 0] 
- [2, 1, 0]
- [2, 3, 1, 0]
- [4, 3, 1, 0]
- [4, 3, 5, 1, 0]

같은 거면 덮어쓰기 식인데. 이러면 단점은 shifting이 수반된다는 것임. 
결국 O(N^2)
무조건 뒤에만 붙게 할 방법은 없을까? 

-- 
p_i가 입력된 시점에
그 p_i보다 작은 p_x는 가지고 있을 필요가 없다.,
*/

class StockSpanner {
    private Deque<int[]> stack;
    
    public StockSpanner() {
        this.stack = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int span = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }
        
        stack.push(new int[] {price, span});

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */