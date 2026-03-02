import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static class Point implements Comparable<Point> {
        long x;
        long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x == p.x) {
                return Long.compare(this.y, p.y);
            }
            return Long.compare(this.x, p.x);
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        Point a = new Point(x1, y1);
        Point b = new Point(x2, y2);
        Point c = new Point(x3, y3);
        Point d = new Point(x4, y4);

        int r1 = ccw(a, b, c) * ccw(a, b, d);
        int r2 = ccw(c, d, a) * ccw(c, d, b);

        int ans = 0;
        if (r1 == 0 && r2 == 0) {
            if (a.compareTo(b) > 0) {
                Point temp = a;
                a = b;
                b = temp;
            }
            if (c.compareTo(d) > 0) {
                Point temp = c;
                c = d;
                d = temp;
            }
            
            if (a.compareTo(d) <= 0 && c.compareTo(b) <= 0) {
                ans = 1;
            }
        } else if (r1 <= 0 && r2 <= 0) {
            ans = 1;
        }

        System.out.println(ans);
    }

    public static int ccw(Point a, Point b, Point c) {
        long result = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);

        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }
    
}