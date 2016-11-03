import java.util.*;

/**
 * Created by Alyssa on 8/5/2016.
 */
public class Prob3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] X = new int[N];
        int[] Y = new int[N];
        for(int i = 0; i < N; i++) {
            X[i] = sc.nextInt();
        }
        for(int i = 0; i < N; i++) {
            Y[i] = sc.nextInt();
        }

        System.out.print(solution(X, Y));
    }


    public static int solution(int[] X, int[] Y) {
        List<Point> p = new ArrayList<>();
        for (int i = 0; i < X.length; i++) {
            p.add(new Point(X[i], Y[i]));
        }
        int N = X.length;

        p.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x != o2.x) {
                    return Double.compare(o1.x, o2.x);
                }
                return Double.compare(o1.y, o2.y);
            }
        });

        HashMap<Double,List<Point>> yVals = new HashMap<>();


        HashMap<Double, List<Point>> xVals = new HashMap<>();
        for(Point curr: p) {
            List<Point> yTemp;
            if(yVals.containsKey(curr.y)) {
                yTemp = yVals.get(curr.y);
            } else {
                yTemp = new ArrayList<>();
            }
            yTemp.add(curr);
            yVals.put(curr.y, yTemp);

            List<Point> xTemp;
            if(xVals.containsKey(curr.x)) {
                xTemp = xVals.get(curr.x);
            } else {
                xTemp = new ArrayList<>();
            }
            xTemp.add(curr);
            xVals.put(curr.x, xTemp);
        }


        List<Point> xCents = new ArrayList<>();
        for(Double key: xVals.keySet()) {
            List<Point> xs = xVals.get(key);
            int size = xs.size();
            int st = 0;
            while(st + 1 < size) {
                int c = st + 1;
                while(c < size) {
                    xCents.add(new Point(xs.get(st).x, (xs.get(st).y + xs.get(c).y) / 2.0));
                    c++;
                }
                st++;
            }
        }

        List<Point> yCents = new ArrayList<>();
        for(Double key: yVals.keySet()) {
            List<Point> ys = yVals.get(key);
            int size = ys.size();
            int st = 0;
            while(st + 1 < size) {
                int c = st + 1;
                while(c < size) {
                    yCents.add(new Point((ys.get(st).x + ys.get(c).x) / 2.0, ys.get(st).y));
                    c++;
                }
                st++;
            }
        }

        xCents.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x != o2.x) {
                    return Double.compare(o1.x, o2.x);
                }
                return Double.compare(o1.y, o2.y);
            }
        });

        yCents.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x != o2.x) {
                    return Double.compare(o1.x, o2.x);
                }
                return Double.compare(o1.y, o2.y);
            }
        });

        int sum = 0;
        int ix = 0;
        int iy = 0;
        while((ix < xCents.size())&&(iy < yCents.size())) {
            int comp = xCents.get(ix).compareTo(yCents.get(iy));
            if (comp == 0) {
                sum++;
                iy++;
            } else if (comp < 0) {
                ix++;
            } else {
                iy++;
            }
        }

        return sum;
    }

    public static class Point implements Comparable<Point>{
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int compare(Point o1, Point o2) {
            if(o1.x != o2.x) {
                return Double.compare(o1.x, o2.x);
            }
            return Double.compare(o1.y, o2.y);
        }

        public int compareTo(Point o) {
            return this.compare(this, o);
        }
    }
}
