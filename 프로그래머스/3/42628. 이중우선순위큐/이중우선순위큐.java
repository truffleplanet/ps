import java.util.*;

class Solution {
    
    static class TreeNode implements Comparable<TreeNode>{
        int id;
        int val;
        
        public TreeNode(int id, int val) {
            this.id = id;
            this.val = val;
        }
        
        @Override
        public int compareTo(TreeNode o) {
            int v1 = Integer.compare(this.val, o.val);
            if (v1 != 0)
                return v1;
            return Integer.compare(this.id, o.id);
        }
    }
    
    public int[] solution(String[] operations) {
        StringTokenizer st;
        
        NavigableSet<TreeNode> treeset = new TreeSet<>();
        int regNo = 0;
        
        
        for (String operation : operations) {
            st = new StringTokenizer(operation);
            char cmd = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());
            
            switch (cmd) {
                case 'I':
                    treeset.add(new TreeNode(regNo++, v));
                    break;
                case 'D':
                    if (treeset.isEmpty())
                        continue;
                    
                    if (v == 1) 
                        treeset.pollLast();
                    else if (v== -1) 
                        treeset.pollFirst();
                    break;
            }
            
        }
        int min = 0;
        int max = 0;
        if (!treeset.isEmpty()) {
            min = treeset.first().val;
            max = treeset.last().val;
        }
        
        int[] answer = {max, min};
        return answer;
    }
}