import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt();
        String grade;
        if (100 >= score) {
        	if (score >= 90) grade = "A";
            else if (score >= 80) grade = "B";
            else if (score >= 70) grade = "C";
            else if (score >= 60) grade = "D";
            else grade = "F";
        } else grade = "F";

        System.out.println(grade);
    }
}