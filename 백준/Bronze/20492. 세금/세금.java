import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        int x = N / 100;
        sb.append(x * 78);
        sb.append(' ');
        sb.append(x * 80 + (x * 20 * 78 / 100));
        System.out.println(sb);

    }
}