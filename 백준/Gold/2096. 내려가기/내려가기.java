import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(tokens[j]);
			}
		}

		int[][] minTable = new int[N][3];
		int[][] maxTable = new int[N][3];

		minTable[0][0] = maxTable[0][0] = arr[0][0];
		minTable[0][1] = maxTable[0][1] = arr[0][1];
		minTable[0][2] = maxTable[0][2] = arr[0][2];

		for (int i = 1; i < N; i++) {
			int max1 = Math.max(maxTable[i - 1][0], maxTable[i - 1][1]);
			int min1 = Math.min(minTable[i - 1][0], minTable[i - 1][1]);
			maxTable[i][0] += max1 + arr[i][0];
			minTable[i][0] += min1 + arr[i][0];

			int max2 = Math.max(max1, maxTable[i - 1][2]);
			int min2 = Math.min(min1, minTable[i - 1][2]);
			maxTable[i][1] += max2 + arr[i][1];
			minTable[i][1] += min2 + arr[i][1];

			int max3 = Math.max(maxTable[i - 1][2], maxTable[i - 1][1]);
			int min3 = Math.min(minTable[i - 1][2], minTable[i - 1][1]);
			maxTable[i][2] += max3 + arr[i][2];
			minTable[i][2] += min3 + arr[i][2];
		}

		int min = Math.min(Math.min(minTable[N - 1][0], minTable[N - 1][1]), minTable[N - 1][2]);
		int max = Math.max(Math.max(maxTable[N - 1][0], maxTable[N - 1][1]), maxTable[N - 1][2]);
		System.out.println(max + " " + min);
	}

}
