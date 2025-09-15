import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] seq = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			seq[i] = i;
		}

		for (int c = 0; c < M; c++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			swap(seq, i, j);
		}

		for (int i = 1; i <= N; i++) {
			sb.append(seq[i]).append(' ');
		}

		System.out.println(sb);

	}

	static void swap(int[] arr, int i, int j) {
		while (i < j) {
			int tmp = arr[j];
			arr[j] = arr[i];
			arr[i] = tmp;
			j--;
			i++;
		}
	}

}
