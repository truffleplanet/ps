import java.util.*;

class Solution {
    
    static class HeapNode implements Comparable<HeapNode> {
        int id;
        int regTime;
        int exTime;
        
        public HeapNode(int id, int regTime, int exTime) {
            this.id = id;
            this.regTime = regTime;
            this.exTime = exTime;
        }
        
        @Override
        public int compareTo(HeapNode o) {
            int v1 = Integer.compare(this.exTime, o.exTime);
            if (v1 != 0)
                return v1;
            int v2 = Integer.compare(this.regTime, o.regTime);
            if (v2 != 0)
                return v2;
            return Integer.compare(this.id, o.id);
        }
    }
    
    public int solution(int[][] jobs) {
        int N = jobs.length;
        int answer = 0;
        int cur = 0;
        int total = 0;
        
        int[][] sortedJobs = new int[N][3];
        for (int i = 0; i < N; i++) {
            sortedJobs[i][0] = i;
            sortedJobs[i][1] = jobs[i][0];
            sortedJobs[i][2] = jobs[i][1];
        }
        
        Arrays.sort(sortedJobs, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        PriorityQueue<HeapNode> pq = new PriorityQueue<>();
        for (int i =0; i < N; i++) {
            int[] task = sortedJobs[i];   
            if (task[0] <= cur) {
                pq.offer(new HeapNode(task[0], task[1], task[2]));
                continue;
            }

            if (pq.isEmpty()) {
                cur = task[0];
                pq.offer(new HeapNode(task[0], task[1], task[2]));
                continue;
            }
            
            while (!pq.isEmpty() && cur < task[0]) {
            HeapNode node = pq.poll();
            cur += node.exTime;
            total += cur - node.regTime;  
        }
            if (cur < task[0]) 
                cur = task[0];
                pq.offer(new HeapNode(task[0], task[1], task[2]));
            
            
        }
        
        while (!pq.isEmpty()) {
            HeapNode node = pq.poll();

            if (cur < node.regTime) {
                cur = node.regTime;
            }

            cur += node.exTime;
            total += cur - node.regTime;

        }
        
        answer = total / N;
                
        return answer;
    }
}