import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String line = sc.nextLine();
			
			if (line.equals("0")) {
				break;
			}
			if (pelindrome(line)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
	
	public static boolean pelindrome(String s) {
		
		int start = 0;
		int end = s.length() - 1;
		
		while (start < end) {
			if (s.charAt(end) != s.charAt(start)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}
