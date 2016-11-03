import java.util.Scanner;

/**
 * Created by Alyssa on 6/5/2016.
 */
public class SharingCandies {
    static int T;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            long c = sc.nextInt();
            long d = sc.nextInt();
            long delta = Math.abs(a - b);
            long lcm = c * d;
            eachTrial:
            while ((a < lcm) || (b < lcm)) {
                if (a < b) {
                    a += c;
                } else {
                    b += d;
                }
                long newDelta = Math.abs(a - b);
                if (newDelta < delta) {
                    delta = newDelta;
                    if (delta == 0) {
                        break eachTrial;
                    }
                }
            }
            System.out.println(delta);
        }
    }
}
