import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * loop invariant -> 좌표의 합은 짝수이다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        final long N = Long.parseLong(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        if (K == 0) {
            if (N == 1) {
                System.out.println(0);
                return;
            }
            long ans = (2L * N) * M - 1L;
            System.out.println(ans);
            return;
        }

        List<Long> deserts = new ArrayList<>(K);
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            long v = Long.parseLong(st.nextToken());
            if (v % 2 == 0) {
                deserts.add(v);
            }
        }

        if (N == 1) {
            long MAX = 2 * (M - 1);
            for (long desert : deserts) {
                if (desert <= MAX) {
                    System.out.println(desert);
                    return;
                }
            }
            System.out.println(0);
            return;
        }

        final long MAX_MOVE_DISTANCE = 2 * N;

        long pos = 0;
        for (int i = 0; i < M - 1; i++) {
            long nextPos = pos + MAX_MOVE_DISTANCE;
            while (Collections.binarySearch(deserts, nextPos) >= 0) {
                nextPos -= 2;
            }

            if (nextPos == pos) {
                pos += MAX_MOVE_DISTANCE;
                System.out.println(pos);
                return;
            } else {
                pos = nextPos;
            }
        }
        pos += MAX_MOVE_DISTANCE - 1;
        System.out.println(pos);
    }
}
