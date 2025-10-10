import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
    static char[] toChar = {'0', '1', '2', '3'};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        N = Integer.parseInt(br.readLine());

        char[] s = new char[N];
		backtrack(s, 0);
        
	}

	public static boolean backtrack(char[] s, int len) {
		if (len == N) {
            System.out.println(s);
			return true;
		}

		for (int i = 1; i <= 3; i++) {
			s[len] = toChar[i];
			if (isGood(s, len + 1)) {
				if (backtrack(s, len + 1))
					return true;
			}
		}
		return false;
	}

	public static boolean isGood(char[] s, int len) {
		int R = len / 2;

		for (int k = 1; k <= R; k++) {
			boolean isMatch = true;
			int i = len - 1;
			int j = i - k;
			for (int c = 0; c < k; c++) {
				if (s[i] != s[j]) {
					isMatch = false;
					break;
				}
				i--;
				j--;
			}
			if (isMatch) {
				return false;
			}
		}
		return true;
	}
}