import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // input
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());        

        int MAX_NUM;
        int[] cards;
        int[] pos;
        int[] scores;

        MAX_NUM = 0;
        cards = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int card = Integer.parseInt(st.nextToken());
            cards[i] = card;
            MAX_NUM = Math.max(MAX_NUM, card);
        }

        pos = new int[MAX_NUM + 1];
        for (int i = 1; i <= N; i++) {
            pos[cards[i]] = i;
        }

        scores = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int card = cards[i];
            for (int m = card * 2; m <= MAX_NUM; m += card) {
                if (pos[m] != 0) {
                    scores[pos[m]]--;
                    scores[pos[card]]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(scores[i]).append(' ');
        }
        System.out.println(sb);
    }
}