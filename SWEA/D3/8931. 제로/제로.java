import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            Deque<Integer> stack = new ArrayDeque<>();
            int K = Integer.parseInt(br.readLine());
            int ans = 0;

            for (int k = 0; k < K; k++) {
                int val = Integer.parseInt(br.readLine());
                if (val == 0) { // 입력이 지울 수가 있음을 보장한다니깐.. 없으면 && !stack.isEmpty()
                    ans -= stack.pop();
                } else {
                    stack.push(val);
                    ans += val;
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        
        System.out.println(sb);
    }
}
