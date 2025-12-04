import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int[] classes = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            classes[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        long total = 0; 
        for (int numStudents: classes) {
            numStudents -= B;
            total++;
            if (numStudents > 0) {
                total += (numStudents / C);
                if (numStudents % C != 0) {
                    total++;
                }
            }
        }

        System.out.println(total);
        
    }
}