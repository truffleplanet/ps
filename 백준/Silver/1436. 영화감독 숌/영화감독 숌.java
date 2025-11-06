import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int rank = 0;
        int i = 666;
        while (true) {
            String mName = String.valueOf(i++);
            if (mName.contains("666")) {
                rank++;
            }

            if (rank == N) {
                System.out.println(mName);
                return;
            }
        }
    }
}