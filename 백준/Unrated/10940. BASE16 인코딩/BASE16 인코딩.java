import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        for (char ch : br.readLine().toCharArray()) {
            int v = ch;
            int nibble1 = (v >> 4) & 0xF;
            int nibble2 = v & 0xF;

            sb.append(toHexChar(nibble1));
            sb.append(toHexChar(nibble2));
        }

        System.out.println(sb.toString().toUpperCase());
    }

    public static char toHexChar(int nibble) {
        if (nibble < 10) 
            return (char) ('0' + nibble);
        return (char) ('a' + (nibble - 10));
    }
}