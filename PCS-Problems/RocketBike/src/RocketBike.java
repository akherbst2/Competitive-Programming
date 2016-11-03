import java.util.Scanner;

/**
 * Created by Alyssa on 4/20/2016.
 */
public class RocketBike {
    static int B, D;
    static final double EPS = 10e-15;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        B = sc.nextInt();
        D = sc.nextInt();
        if (B > 0) {
            double minTime = binarySearchHeight(0, 20, B);
            double minStartDistance = binarySearchV(0, D, D / minTime);
            System.out.println(minStartDistance);
        }
        else {
            System.out.println(0);
        }
    }

    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < EPS;
    }

    //To find T, given h= B
    public static double binarySearchHeight(double t0, double t1, double targH) {
        if (isEqual(t0, t1)) {
            return t1;
        }
        double tmid = (t1 + t0)/2;
        double hmid = -h(tmid);
        if (hmid < targH) {
            return binarySearchHeight(tmid, t1, targH);
        }
        else if (hmid > targH){
            return binarySearchHeight(t0, tmid, targH);
        }
        else return tmid;
    }

    public static double binarySearchV(double d0, double d1, double targV) {
        if (isEqual(d0, d1)) {
            return d1;
        }
        double mid = (d1 + d0)/2;
        double vmid = V(mid);
        if (vmid < targV) {
            return binarySearchV(mid, d1, targV);
        }
        else if (V(mid) > targV) {
            return binarySearchV(d0, mid, targV);
        }
        else return mid;
    }


    public static double h(double t) {
        return -.5 * 9.81 * t * t;
    }

    //Given running-start distance
    public static double V(double d) {
        return 2*Math.pow(d, 5) + 3*Math.pow(d, 4) + d;
    }
}
