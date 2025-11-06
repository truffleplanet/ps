import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();

        int intA = Integer.parseInt(A);
        int intB = Integer.parseInt(B);
        int intC = Integer.parseInt(C);
        sb.append(intA + intB - intC).append('\n');
        sb.append(Integer.parseInt(A.concat(B)) - intC);
        System.out.println(sb);
    }
}