import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String X = String.valueOf(Integer.parseInt(br.readLine()) * 
            Integer.parseInt(br.readLine()) *
            Integer.parseInt(br.readLine()));

        int[] count = new int[10];
        for (char ch : X.toCharArray()) {
            count[ch - '0']++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(count[i]).append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}