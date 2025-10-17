import java.util.*;


class Solution {
    public int solution(int n, int w, int num) {
        
        int h = n / w;
        if (n % w != 0) {
            h += 1;
        } 
        
        int[][] storage = new int[h][w];
        
        int k = 1;
        int i = h - 1;
        int j = 0;
        int d = 1;
        
        int r = 0;
        int c = 0;
        
        while (k <= n){
            
            if (j >= w) {
                d = -1;
                j--;
                i--;
            }
            
            if (j < 0) {
                d = 1;
                j++;
                i--;
            }
            
            if (k == num) {
                r = i;
                c = j;
            }
            
            storage[i][j] = k;
            k++;
            j += d;
            
        }
        
        int ans = 0;
        for (int p = 0; p <= r; p++) {
            if (storage[p][c] != 0)
                ans++;
        }
        
        return ans;
    }
}