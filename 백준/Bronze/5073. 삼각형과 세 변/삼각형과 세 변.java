import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static String Equilateral = "Equilateral";
    final static String Isosceles = "Isosceles";
    final static String Scalene = "Scalene";
    final static String Invalid = "Invalid";
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] lengths = new int[3];
        lengths[0] = Integer.parseInt(st.nextToken());
        lengths[1] = Integer.parseInt(st.nextToken());
        lengths[2] = Integer.parseInt(st.nextToken());
        Arrays.sort(lengths);
        while (lengths[0] != 0 || lengths[1] != 0 || lengths[2] != 0) {
            if (lengths[2] >= lengths[0] + lengths[1]) {
                sb.append(Invalid);
            } else if (lengths[0] == lengths[1] && lengths[0] == lengths[2]) {
                sb.append(Equilateral);
            } else if (lengths[0] == lengths[1] || lengths[1] == lengths[2]) { // ordered
                sb.append(Isosceles);
            } else {
                sb.append(Scalene);
            }
            sb.append('\n');

            st = new StringTokenizer(br.readLine());
            lengths[0] = Integer.parseInt(st.nextToken());
            lengths[1] = Integer.parseInt(st.nextToken());
            lengths[2] = Integer.parseInt(st.nextToken());
            Arrays.sort(lengths);
        }
        System.out.println(sb);
    }
}