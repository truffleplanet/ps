import java.util.*;

class Solution {
    
    class Stage implements Comparable<Stage>{
        int n;
        double ratio;
        
        public Stage(int n) {
            this.n = n;
            this.ratio = 0;
        }
        
        public void getRatio(int x, int y) {
            this.ratio = (double) x / y;
        }
        
        @Override
        public int compareTo(Stage o) {
            int v1 = Double.compare(o.ratio, this.ratio);
            if (v1 != 0) {
                return v1;
            }
            int v2 = Integer.compare(this.n, o.n);
            return v2;
        }
        
    }
    
    public int[] solution(int N, int[] stages) {
        
        Stage[] arr = new Stage[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new Stage(i);
        }
        
        Arrays.sort(stages);
        
        for (int i = 0; i < stages.length / 2; i++) {
            int temp = stages[i];
            stages[i] = stages[stages.length - 1 - i];
            stages[stages.length - 1 - i] = temp;
        }
                
        int total = 0;
        int idx = 0;
        while(idx < stages.length && stages[idx] == N + 1) {
            total++;
            idx++;
        }
        
        for (int i = N; i >= 1; i--) {            
            int unClear = 0;
            while(idx < stages.length && stages[idx] == i) {
                unClear++;
                total++;
                idx++;
            }
            
            if (total != 0) {   
                arr[i].getRatio(unClear, total);
            }
        }
        
        Arrays.sort(arr, 1, arr.length);
        
        int[] answer = new int[N];
        for (int i = 1; i <= N; i++) {
            answer[i - 1] = arr[i].n;
        }
        return answer;
    }
}