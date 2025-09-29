import java.util.*;

class Solution {
    
    Set<Integer> visited; 
    boolean[] selected;
    int ans;
    int N;

    public int solution(String numbers) throws Exception {
        this.N = numbers.length();
        this.selected = new boolean[N];
        this.visited = new HashSet<>();
        this.ans = 0;
        
        int[] arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = numbers.charAt(i) - '0';
        }
        
        for (int i = 1; i <= N; i++) {
            this.selected = new boolean[N];
            permutation(arr, i, 0, 0);
        }
                
        return ans;
    }
    
    
    public void permutation(int[] number, int R, int cnt, int val) {
        if (cnt == R) {
            if (!visited.contains(val) && isPrime(val)) {
                ans++;
            }
            visited.add(val);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                int nv = val * 10 + number[i];
                selected[i] = true;
                permutation(number, R, cnt + 1, nv);
                selected[i] = false;
            }
        }
    }
    
    public boolean isPrime(int val) {
        int sqrt = (int) Math.sqrt(val);
        
        if (val <= 1) {
            return false;
        }
        
        for (int i = 2; i <= sqrt; i++) {
            if (val % i == 0)
                return false;
        }
        
        return true;
    }
    
}