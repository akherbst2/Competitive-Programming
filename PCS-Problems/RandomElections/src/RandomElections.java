import java.util.Scanner;
/**
 * Total time:  6:18
 * https://pcs.spruett.me/problems/26
 * Created by Alyssa on 4/6/2016.
 */
public class RandomElections {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.print(factorial[n]/factorial[n-k]);

    }

    /**
     * Sets up code by itself.  Doesn't need a method to be called.  Good for lookup tables.
     */
    static long[] factorial = new long[21];
    static {
        factorial[0] = 1;
        for (int i = 1; i < 21; i++) {
            factorial[i] = i * factorial[i - 1];
        }
    }
}
