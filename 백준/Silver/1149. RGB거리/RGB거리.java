import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] prev = new int[3];

		st = new StringTokenizer(br.readLine());
		prev[0] = Integer.parseInt(st.nextToken());
		prev[1] = Integer.parseInt(st.nextToken());
		prev[2] = Integer.parseInt(st.nextToken());

		int[] cur = new int[3];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			cur[0] = r + Math.min(prev[1], prev[2]);
			cur[1] = g + Math.min(prev[0], prev[2]);
			cur[2] = b + Math.min(prev[0], prev[1]);

			int[] tmp = prev;
			prev = cur;
			cur = tmp;
		}

		int ans = Math.min(prev[0], Math.min(prev[1], prev[2]));
		System.out.println(ans);

	}

}
