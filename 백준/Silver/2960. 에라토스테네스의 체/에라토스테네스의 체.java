import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] isComposite = new boolean[N + 1];
        isComposite[1] = true;

        int cnt = 0;
        int val = -1;
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) {
                ++cnt;
                if (cnt == K) {
                    val = i;
                    break;
                }
                for (int j = i + i; j <= N; j += i) {
                    if (isComposite[j]) {
                        continue;
                    }
                    isComposite[j] = true; 
                    ++cnt;
                    if (cnt == K) {
                        val = j;
                        break;
                    }
                }
            }
        }
        System.out.println(val);
        
    }
}