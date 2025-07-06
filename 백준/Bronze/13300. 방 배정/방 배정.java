import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		int N = Integer.parseInt(firstLine[0]);
		int K = Integer.parseInt(firstLine[1]);
		
		int[] classification = new int[13];
		
		for (int i = 0; i < N; i++) {
			String[] line = sc.nextLine().split(" ");
			int sex = Integer.parseInt(line[0]);
			int grade = Integer.parseInt(line[1]);
			
			if (sex == 0) {
				classification[grade] += 1;
			} else {
				classification[grade + 6] += 1;
			}
		}
		sc.close();
		
		int roomNum = 0;
		for (int j = 1; j < 13; j++) {
			roomNum += classification[j] / K;
			if  ((classification[j] % K) > 0) {
				roomNum += 1;
			}
		}
		
		System.out.println(roomNum);
	}
}
