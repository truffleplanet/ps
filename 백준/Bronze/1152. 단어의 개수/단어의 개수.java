import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        char[] seq = br.readLine().toCharArray();
        
        int start = 0;
        int end = seq.length - 1;

        int cnt = 0;
        boolean newWord = true;
        for (int i = start; i <= end; i++) {
            if (seq[i] == ' ')
                newWord = true;
            else if (newWord && seq[i] != ' ') {
                newWord = false;
                cnt++;
            }
        }

        System.out.println(cnt);
        
    }
}