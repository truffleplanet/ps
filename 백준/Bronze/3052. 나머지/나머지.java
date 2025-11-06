import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean[] met = new boolean[42];
        int cnt = 0;

        for (int i = 0; i < 10; i++) {
            int x = Integer.parseInt(br.readLine()) % 42;
            if (!met[x]) {
                met[x] = true;
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}