import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    final static String TERMINATES = "Terminates";
    final static String LOOPS = "Loops";
    final static int INF = 50_000_000;
    final static int MOD = 1 << 8;

    static int s_m;
    static int s_c;
    static int s_i;

    static int[] arr;
    static int p;
    static int p_i;

    static char[] cmd;
    static char[] input;
    
    static HashMap<Integer, Integer> jump;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            s_m = Integer.parseInt(st.nextToken());
            s_c = Integer.parseInt(st.nextToken());
            s_i = Integer.parseInt(st.nextToken());

            arr = new int[s_m];
            p = 0;
            p_i = 0;

            cmd = br.readLine().toCharArray();
            input = br.readLine().toCharArray();

            jump = new HashMap<>();
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < s_c; i++) {
                if (cmd[i] == '[') {
                    stack.push(i);
                } else if (cmd[i] == ']') {
                    int l = stack.pop();
                    int r = i;
                    jump.put(l, r);
                    jump.put(r, l);
                }
            }

            int cnt = 0;
            int i = 0;

            int r = 0;
            int doubleTime = INF * 2;
            while(i < s_c && cnt <= doubleTime) {
                if (cnt > INF) {
                    r = Math.max(r, i);
                }

                switch (cmd[i]) {
                    case '-':
                        subOne();
                        break;
                    case '+':
                        addOne();
                        break;
                    case '<':
                        moveLeft();
                        break;
                    case '>':
                        moveRight();
                        break;
                    case '[':
                        if (arr[p] == 0) {
                            i = jump.get(i);
                        }
                        break;
                    case ']':
                        if (arr[p] != 0) {
                            i = jump.get(i);
                        } 
                        break;
                    case '.': 
                        break;
                    case ',':
                        save();
                        break;
                }
                
                i++;
                cnt++;
            }

            if (cnt <= INF) {
                sb.append(TERMINATES);
            } else {
                sb.append(LOOPS).append(' ').append(jump.get(r)).append(' ').append(r);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void addOne() {
        arr[p] = (arr[p] + 1) % MOD;
    }

    static void subOne() {
        if (arr[p] == 0) {
            arr[p] = 255;
        } else {
            arr[p] = arr[p] - 1;
        }
    }

    static void moveLeft() {
        if (p == 0) {
            p = s_m - 1;
        } else {
            p = p - 1;
        }
    }

    static void moveRight() {
        p = (p + 1) % s_m;
    }

    static void save() {
        if (p_i == s_i) {
            arr[p] = 255;
        } else {
            arr[p] = (int) input[p_i++];
        }
    }

}