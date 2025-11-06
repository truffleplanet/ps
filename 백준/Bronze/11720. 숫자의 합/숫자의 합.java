import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        br.readLine();
        int sum = 0;
        for (char ch : br.readLine().toCharArray()) {
            sum += ch - '0';
        }

        System.out.println(sum);
    }
}