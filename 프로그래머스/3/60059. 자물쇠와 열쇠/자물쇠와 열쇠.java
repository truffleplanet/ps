/*
lock의 0파트를 모두 채울 수 있는지 확인해야함.


*/

import java.util.*;

class Solution {
        
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;
        int total = N + (2 * M);
        int[][] padded = new int[total][total];
        
        for (int i = 0; i < total; i++) {
            Arrays.fill(padded[i], -1);
        }
        
        Set<Integer> ans = new HashSet<>();
        
        for (int i = M; i < N + M; i++) {
            for (int j = M; j < N + M; j++) {
                int val = lock[i - M][j - M];
                padded[i][j] = val;
                
                if (val == 0) {
                    ans.add(i * total + j);
                }
            }
        }
        
        // for (int[] row : padded) {
        //     System.out.println(Arrays.toString(row));
        // }
        
        
        int[][][] keys = new int[4][][];
        keys[0] = key;
        keys[1] = rotate(keys[0]);
        keys[2] = rotate(keys[1]);
        keys[3] = rotate(keys[2]);
        
        for (int i = 0; i < N + M; i++) {
            for (int j = 0; j < N + M; j++) {
                for (int d = 0; d < 4; d++) {
                    
                    boolean can = true;
                    Set<Integer> matches = new HashSet<>();
                    
                    for (int r = 0; r < M; r++) {
                        for (int c = 0; c < M; c++) {
                            if (keys[d][r][c] == padded[i + r][j + c]) {
                                // System.out.println("key: " + keys[d][r][c]);
                                // System.out.println("lock: " + padded[i + r][j + c]);
                                can = false;
                                break;
                            }
                            
                            if (keys[d][r][c] == 1 && padded[i + r][j + c] == 0) {
                                matches.add((i + r) * total + j + c);
                            }
                        }
                        
                        if (!can)
                            break;
                    }
                    
                    // System.out.println(ans);
                    // System.out.println(matches);
                    
                    if (can && ans.equals(matches))
                        return true;
                }
            }
        }
        
        return false;
    }
    
    public int[][] rotate(int[][] key) {
        int N = key.length;
        int[][] out = new int[N][N];
        
        for (int c = 0, i = N - 1; c < N; c++, i--) {
            for (int r = 0, j = 0; r < N; r++, j++) {
                out[r][c] = key[i][j];
            }
        }
        return out;
        
    }
}