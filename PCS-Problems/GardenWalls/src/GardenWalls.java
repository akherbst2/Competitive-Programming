import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Alyssa on 4/7/2016.
 */
public class GardenWalls {
    static ArrayList<Point> list;
    static double radius;


    public static void main(String[] args) {
        readInput();
        //System.out.print(list);
        ArrayList<Point> outer = convexHull(list);


        Point base = outer.get(0);
        double perim = 0;
        for (Point point:outer) {
            perim += Math.abs(base.dist(point));
            base = point;
        }
        perim += base.dist(outer.get(0));


        perim += (2 * Math.PI * radius);
        System.out.print(perim);



    }

    static Point bottomThenLeft(ArrayList<Point> points) {
        Point bottom = points.get(0);
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.y < bottom.y ||
                    (p.y == bottom.y && p.x < bottom.x)) {
                bottom = p;
            }
        }
        return bottom;
    }

    static void sortByAngle(Point base, ArrayList<Point> points) {
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if (base.isColinear(a, b)) {
                    return Double.compare(base.dist(a), base.dist(b));
                }
                //Is Left Turn
                if (base.isCCW(a, b)) {
                    return -1;
                }
                return 1;
            }
        });
    }

    /**
     * Starts at bottom Point, moves in a CCW fashion
     * @param points
     * @return
     */
    static ArrayList<Point> convexHull(ArrayList<Point> points) {
        Point base = bottomThenLeft(points);
        sortByAngle(base, points);

        Stack<Point> stack = new Stack<Point>();
        stack.push(points.get(0));
        stack.push(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Point candidate = points.get(i);

            while (stack.size() >= 2) {
                Point a = stack.get(stack.size() - 2);
                Point b = stack.peek();
                //Stop popping when point is CCW
                if (a.isCCW(b, candidate)) {
                    break;
                }
                stack.pop();
            }
            stack.push(candidate);
        }
        return new ArrayList<Point>(stack);
    }

    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<Point>();
        double n = sc.nextDouble();
        radius = sc.nextDouble();
        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            Point point = new Point(x, y);
            list.add(point);
        }
    }



//
//    /**
//     * Created by Alyssa on 4/7/2016.
//     */
//    public static class Line extends Line2D.Double {
//        double slope;
//        double length;
//        static final double EPS = 1e-1;
//
//        public Line(double a1, double b1, double a2, double b2) {
//            super(a1, b1, a2, b2);
//            if (Math.abs(x2 - x1) < EPS) {
//                slope = java.lang.Double.POSITIVE_INFINITY;
//            }
//            else {
//                slope = (y2 - y1) / (x2 - x1);
//            }
//        }
//
//        public double crossProduct(Line other) {
//            return Math.abs(this.length * other.length) * Math.sin(angle(other));
//        }
//
//        public boolean equals(Line other) {
//            boolean B1 = Math.abs(y2 - other.y1) < EPS;
//            boolean B2 = Math.abs(x2 - other.x2) < EPS;
//            boolean B3 = Math.abs(x1 - other.x1) < EPS;
//            boolean B4 = Math.abs(y1 - other.y1) < EPS;
//            return B1&&B2&&B3&&B4;
//        }
//
//        //x1 = lin[0], y1 = lin[1], x2 = lin[2], y2 = lin[3]
//        public boolean intersects(Line other) {
//            double A1 = y2 - y1;
//            double B1 = x2 - x1;
//            double C1 = A1 * x1 + B1 * y1;
//
//            double A2 = other.y2 - other.y1;
//            double B2 = other.x2 - other.x1;
//            double C2 = A2 * other.x1 + B2 * other.y1;
//
//            double det = A1*B2 - A2 * B1;
//            return (det == 0) ? false:true;
//        }
//
//        public Point getPointOn(Line other) {
//            double A1 = y2 - y1;
//            double B1 = x1 - x2;
//            double C1 = A1 * x1 + B1 * y1;
//
//            double A2 = other.y2 - other.y1;
//            double B2 = other.x1 - other.x2;
//            double C2 = A2 * other.x1 + B2 * other.y1;
//
//            double det = A1*B2 - A2 * B1;
//            if (det == 0) {
//                throw new IllegalStateException("Error in method getPointOn(): \nCurrent line does not have an Intersecting line.");
//            }
//            double x = (B2*C1 - B1*C2)/det;
//            double y = (A1*C2 - A2*C1)/det;
//            return new Point(x, y);
//        }
//
//        public double angle(Line other) {
//            return Math.acos(dotProd(other)/(length*other.length));
//        }
//
//        public double angleToDeg(Line other) {
//            return Math.toDegrees(angle(other));
//        }
//
//        public double dotProd(Line other) {
//            double xProd = (x2 - x1)*(other.x2 - other.x1);
//            double yProd = (y2 - y1)*(other.y2 - other.y1);
//            return xProd + yProd;
//        }
//    }

//    /**
//     * Created by Alyssa on 4/7/2016.
//     */
//    public static class Point extends Point2D.Double {
//
//        public Point(double x, double y) {
//            super(x, y);
//        }
//
//        public Point sub(Point o) {
//            return new Point(x - o.x, y - o.y);
//        }
//
//        public double dist(Point a) {
//            return Math.sqrt(Math.pow((a.x - x), 2) + Math.pow((a.y - y ), 2));
//        }
//
//        public boolean isColinear(Point a, Point b) {
//            Line line = new Line(a.x, a.y, b.x, b.y);
//            return line.contains(this);
//        }
//
//        boolean isCCW(Point b, Point c) {
//            // a point that creates a vector from the origin
//            b = b.sub(this);
//            c = c.sub(this);
//            long cross = b.x*c.y - b.y*c.x;
//            //If the cross product points upwards
//            return cross > 0;
//        }
//    }


    public static class Point {
        final long x, y;
        final double EPS = 1e-6;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public Point sub(Point o) {
            return new Point(x - o.x, y - o.y);
        }

        public double dist(Point a) {
            return Math.sqrt(Math.pow((a.x - x), 2) + Math.pow((a.y - y ), 2));
        }

        public boolean isColinear(Point a, Point b) {
            a = a.sub(this);
            b = b.sub(this);
            long cross = a.x*b.y - a.y*b.x;
            return Math.abs(cross - 0) <= EPS;
        }

        boolean isCCW(Point b, Point c) {
            // a point that creates a vector from the origin
            b = b.sub(this);
            c = c.sub(this);
            long cross = b.x*c.y - b.y*c.x;
            //If the cross product points upwards
            return cross > 0;
        }
    }

}
