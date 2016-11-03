import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Geometry Problem
 *
 * Created by Alyssa on 4/7/2016.
 */
public class BadNoteTaking {


    static double[][] brd = new double[4][4];
    static ArrayList<Line> list;
    static final double EPS = 1e-5;

    public static void main(String[] args) {
        readInput();
       // System.out.println(Math.abs(list.get(2).angleToDeg(list.get(3))));


        System.out.println(isTicTacToe(list)? "NOT NOTES":"NOTES");
    }


    static boolean isTicTacToe(ArrayList<Line> lines) {
        Line init = lines.get(0);
        for(Line line:lines) {
            if (Math.abs(line.length - init.length) > EPS) {
                return false;
            }
            boolean hasOneThird = false;
            boolean hasTwoThird = false;
            double piOverFour = Math.PI / 4;
            for(Line other:lines) {
                if (line.equals(other)) {
                    continue;
                }
                //Is perp
                //The parallel one is checked in other checks
                //.out.println(line.angleToDeg(other));
                if ((Math.abs(line.angleToDeg(other) - 90) < EPS )||((Math.abs(line.angleToDeg(other) + 90) < EPS ))){
                    Point p = line.getPointOn(other);
                    Line segment = new Line(line.x1, line.y1, p.x, p.y);
                    if (Math.abs(segment.length - (line.length/ 3)) < EPS) {
                        hasOneThird = true;
                    }
                    else if (Math.abs(segment.length - (line.length/ 3) * 2) < EPS) {
                        hasTwoThird = true;
                    }
                    else return false;

                }
                else if (!((Math.abs(line.angleToDeg(other) - 180) < EPS )||((Math.abs(line.angleToDeg(other) + 180) < EPS ))||(Math.abs(line.angleToDeg(other)) < EPS))) {
                    return false;
                }
            }
            //Checks to see if has a one third and has a two third
            if (!(hasOneThird && hasTwoThird)) {
                return false;
            }

        }
        return true;
    }






    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<Line>();
        for (int i = 0; i < 4; i++) {
            double x1 = sc.nextDouble();
            double y1 = sc.nextDouble();
            double x2 = sc.nextDouble();
            double y2 = sc.nextDouble();
            Line lin = new Line(x1, y1, x2, y2);
            list.add(lin);
        }
    }


    public static class Line extends Line2D.Double {
        double slope;
        double length;
        static final double EPS = 1e-5;

        public Line(double a1, double b1, double a2, double b2) {
            super(a1, b1, a2, b2);
            if (Math.abs(x2 - x1) < EPS) {
                slope = java.lang.Double.POSITIVE_INFINITY;
            }
            else {
                slope = (y2 - y1) / (x2 - x1);
            }
            length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        }

        public double crossProduct(Line other) {
            return Math.abs(this.length * other.length) * Math.sin(angle(other));
        }

        public boolean equals(Line other) {
            boolean B1 = Math.abs(y2 - other.y2) < EPS;
            boolean B2 = Math.abs(x2 - other.x2) < EPS;
            boolean B3 = Math.abs(x1 - other.x1) < EPS;
            boolean B4 = Math.abs(y1 - other.y1) < EPS;
            return B1&&B2&&B3&&B4;
        }

        //x1 = lin[0], y1 = lin[1], x2 = lin[2], y2 = lin[3]
        public boolean intersects(Line other) {
            double A1 = y2 - y1;
            double B1 = x2 - x1;
            double C1 = A1 * x1 + B1 * y1;

            double A2 = other.y2 - other.y1;
            double B2 = other.x2 - other.x1;
            double C2 = A2 * other.x1 + B2 * other.y1;

            double det = A1*B2 - A2 * B1;
            return (det == 0) ? false:true;
        }

        public Point getPointOn(Line other) {
            double A1 = y2 - y1;
            double B1 = x1 - x2;
            double C1 = A1 * x1 + B1 * y1;

            double A2 = other.y2 - other.y1;
            double B2 = other.x1 - other.x2;
            double C2 = A2 * other.x1 + B2 * other.y1;

            double det = A1*B2 - A2 * B1;
            if (det == 0) {
                throw new IllegalStateException("Error in method getPointOn(): \nCurrent line does not have an Intersecting line.");
            }
            double x = (B2*C1 - B1*C2)/det;
            double y = (A1*C2 - A2*C1)/det;
            return new Point(x, y);
        }

        public double angle(Line other) {
            //System.out.println(dotProd(other) + "  " + length * other.length + "  " + length + "  " + other.length);
            return Math.acos(dotProd(other)/Math.abs(length*other.length));
        }

        public double angleToDeg(Line other) {
            //System.out.println("angle = " + Math.toDegrees(angle(other)));
            return Math.toDegrees(angle(other));
        }

        public double dotProd(Line other) {
            double xProd = (x2 - x1)*(other.x2 - other.x1);
            double yProd = (y2 - y1)*(other.y2 - other.y1);
            return xProd + yProd;
        }
    }

    /**
     *  THIS point class extends Point2D.Double.  Use for small convex hull.  Did NOT work for Garden wall problem.
     * Created by Alyssa on 4/7/2016.
     */
    public static class Point extends Point2D.Double {


        public Point sub(Point o) {
            return new Point(x - o.x, y - o.y);
        }

        public double dist(Point a) {
            return Math.sqrt(Math.pow((a.x - x), 2) + Math.pow((a.y - y ), 2));
        }

public boolean isColinear(Point a, Point b) {
    a = a.sub(this);
    b = b.sub(this);
    double cross = a.x*b.y - a.y*b.x;
    return Math.abs(cross - 0) <= EPS;
}
        boolean isCCW(Point b, Point c) {
            // a point that creates a vector from the origin
            b = b.sub(this);
            c = c.sub(this);
            double cross = b.x*c.y - b.y*c.x;
            //If the cross product points upwards
            return cross > 0;
        }
    }



}
