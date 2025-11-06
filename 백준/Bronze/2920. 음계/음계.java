import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String ascending = "1 2 3 4 5 6 7 8";
        String descending = "8 7 6 5 4 3 2 1";

        String in = br.readLine();

        if (in.equals(ascending)) {
            System.out.println("ascending");
        } else if (in.equals(descending)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }

    }
}